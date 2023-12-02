package com.rany.cake.devops.base.service.ssh;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JSCHTool {

    public static void main(String[] args) throws JSchException {
        String host = "127.0.0.1";
        String user = "yuanjinxiu";
        String password = "131400";
        int port = 22;

        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, port);
        session.setPassword(password);

        // 关闭 StrictHostKeyChecking，避免 UnknownHostKey 导致连接失败
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);

        // 连接到服务器
        session.connect();
        remoteExecute(session, "ls -l");
        remoteExecute(session, "pwd");
        remoteExecute(session, "mkdir /Users/yuanjinxiu/jsch-demo");
        remoteExecute(session, "ls /Users/yuanjinxiu/jsch-demo");
        remoteExecute(session, "touch /Users/yuanjinxiu/jsch-demo/test1; touch /Users/yuanjinxiu/jsch-demo/test2");
        remoteExecute(session, "echo 'It a test file.' > /Users/yuanjinxiu/jsch-demo/test-file");
        remoteExecute(session, "cat /Users/yuanjinxiu/jsch-demo/test-file");

        scpTo(session, "/Users/yuanjinxiu/workspace/cake-devops-base/docker-compose/build.sh", "/Users/yuanjinxiu/jsch-demo/build.sh");
        System.out.println("hello");
        session.disconnect();
    }

    public static List<String> remoteExecute(Session session, String command) throws JSchException {
        log.info(">> {}", command);
        List<String> resultLines = new ArrayList<>();
        ChannelExec channel = null;
        try {
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            InputStream input = channel.getInputStream();
            channel.connect();
            try {
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(input));
                String inputLine = null;
                while ((inputLine = inputReader.readLine()) != null) {
                    log.info("{}", inputLine);
                    resultLines.add(inputLine);
                }
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (Exception e) {
                        log.error("JSch inputStream close error:", e);
                    }
                }
            }
        } catch (IOException e) {
            log.error("IOcxecption:", e);
        } finally {
            if (channel != null) {
                try {
                    channel.disconnect();
                } catch (Exception e) {
                    log.error("JSch channel disconnect error:", e);
                }
            }
        }
        return resultLines;
    }

    public static long scpTo(Session session, String source, String destination) {
        FileInputStream fileInputStream = null;
        try {
            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            OutputStream out = channel.getOutputStream();
            InputStream in = channel.getInputStream();
            boolean ptimestamp = false;
            String command = "scp";
            if (ptimestamp) {
                command += " -p";
            }
            command += " -t " + destination;
            channel.setCommand(command);
            channel.connect(2000);
            if (checkAck(in) != 0) {
                return -1;
            }
            File _lfile = new File(source);
            if (ptimestamp) {
                command = "T " + (_lfile.lastModified() / 1000) + " 0";
                // The access time should be sent here,
                // but it is not accessible with JavaAPI ;-<
                command += (" " + (_lfile.lastModified() / 1000) + " 0\n");
                out.write(command.getBytes());
                out.flush();
                if (checkAck(in) != 0) {
                    return -1;
                }
            }
            //send "C0644 filesize filename", where filename should not include '/'
            long fileSize = _lfile.length();
            command = "C0644 " + fileSize + " ";
            if (source.lastIndexOf('/') > 0) {
                command += source.substring(source.lastIndexOf('/') + 1);
            } else {
                command += source;
            }
            command += "\n";
            out.write(command.getBytes());
            out.flush();
            if (checkAck(in) != 0) {
                return -1;
            }
            //send content of file
            fileInputStream = new FileInputStream(source);
            byte[] buf = new byte[1024];
            long sum = 0;
            while (true) {
                int len = fileInputStream.read(buf, 0, buf.length);
                if (len <= 0) {
                    break;
                }
                out.write(buf, 0, len);
                sum += len;
            }
            //send '\0'
            buf[0] = 0;
            out.write(buf, 0, 1);
            out.flush();
            if (checkAck(in) != 0) {
                return -1;
            }
            return sum;
        } catch (JSchException e) {
            log.error("scp to catched jsch exception, ", e);
        } catch (IOException e) {
            log.error("scp to catched io exception, ", e);
        } catch (Exception e) {
            log.error("scp to error, ", e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e) {
                    log.error("File input stream close error, ", e);
                }
            }
        }
        return -1;
    }

    public static long scpFrom(Session session, String source, String destination) {
        FileOutputStream fileOutputStream = null;
        try {
            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand("scp -f " + source);
            OutputStream out = channel.getOutputStream();
            InputStream in = channel.getInputStream();
            channel.connect();
            byte[] buf = new byte[1024];
            //send '\0'
            buf[0] = 0;
            out.write(buf, 0, 1);
            out.flush();
            while (true) {
                if (checkAck(in) != 'C') {
                    break;
                }
            }
            //read '644 '
            in.read(buf, 0, 4);
            long fileSize = 0;
            while (true) {
                if (in.read(buf, 0, 1) < 0) {
                    break;
                }
                if (buf[0] == ' ') {
                    break;
                }
                fileSize = fileSize * 10L + (long) (buf[0] - '0');
            }
            String file = null;
            for (int i = 0; ; i++) {
                in.read(buf, i, 1);
                if (buf[i] == (byte) 0x0a) {
                    file = new String(buf, 0, i);
                    break;
                }
            }
            // send '\0'
            buf[0] = 0;
            out.write(buf, 0, 1);
            out.flush();
            // read a content of lfile
            if (Files.isDirectory(Paths.get(destination))) {
                fileOutputStream = new FileOutputStream(destination + File.separator + file);
            } else {
                fileOutputStream = new FileOutputStream(destination);
            }
            long sum = 0;
            while (true) {
                int len = in.read(buf, 0, buf.length);
                if (len <= 0) {
                    break;
                }
                sum += len;
                if (len >= fileSize) {
                    fileOutputStream.write(buf, 0, (int) fileSize);
                    break;
                }
                fileOutputStream.write(buf, 0, len);
                fileSize -= len;
            }
            return sum;
        } catch (JSchException e) {
            log.error("scp to catched jsch exception, ", e);
        } catch (IOException e) {
            log.error("scp to catched io exception, ", e);
        } catch (Exception e) {
            log.error("scp to error, ", e);
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                    log.error("File output stream close error, ", e);
                }
            }
        }
        return -1;

    }

    private static int checkAck(InputStream in) throws IOException {
        int b = in.read();
        // b may be 0 for success,
        //          1 for error,
        //          2 for fatal error,
        //          -1
        if (b == 0) return b;
        if (b == -1) return b;
        if (b == 1 || b == 2) {
            StringBuffer sb = new StringBuffer();
            int c;
            do {
                c = in.read();
                sb.append((char) c);
            }
            while (c != '\n');
            if (b == 1) { // error
                log.debug(sb.toString());
            }
            if (b == 2) { // fatal error
                log.debug(sb.toString());
            }
        }
        return b;
    }
}
