package com.rany.cake.devops.base.service.utils;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class JSCHTool {
    protected static final Logger log = LoggerFactory.getLogger("RabbitMq");

    public static boolean remoteExecute(Session session, String command) throws JSchException {
        log.info(">> {}", command);
        ChannelExec channel = null;
        boolean executionSuccess = false;
        try {
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            channel.setErrStream(System.err);
            // 设置超时时间（例如15min）
            channel.connect(60 * 1000 * 15);
//            channel.connect();

            try (InputStream input = channel.getInputStream();
                 InputStream errStream = channel.getErrStream();
                 BufferedReader inputReader = new BufferedReader(new InputStreamReader(input));
                 BufferedReader errReader = new BufferedReader(new InputStreamReader(errStream))) {

//                // 读取标准输出
                String line;
                while ((line = inputReader.readLine()) != null) {
                    log.info("Output: {}", line);
                }

                // 读取错误输出
                String errLine;
                while ((errLine = errReader.readLine()) != null) {
                    log.error("Error Output: {}", errLine);
                }

                // 等待命令执行完毕
                while (!channel.isClosed()) {
                    Thread.sleep(100); // 等待100毫秒
                }

                int exitStatus = channel.getExitStatus();
                log.info("Command exited with status: {}", exitStatus);
                return exitStatus == 0;
//            } catch (IOException | InterruptedException e) {
            } catch (Exception e) {
                log.error("Error executing command: {}", command, e);
                // throw new RemoteExecutionException("Failed to execute command", e);
            }
        } finally {
            if (channel != null) {
                try {
                    channel.disconnect();
                } catch (Exception e) {
                    log.error("JSch channel disconnect error:", e);
                }
            }
        }

        return executionSuccess;
    }

}
