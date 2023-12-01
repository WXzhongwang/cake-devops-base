package com.rany.cake.devops.base.service.plugins.ci;

import com.jcraft.jsch.*;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.RunningConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * 推送脚本并执行构建指令
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/20 19:41
 * @email 18668485565163.com
 */

@Slf4j
@Component
public class DeliveryPlugin extends BasePlugin {
    @Override
    public boolean init(DeployContext context) {
        return true;
    }

    @Override
    public boolean execute(DeployContext context) {
        String host = (String) context.getArgMap().get(RunningConstant.BUILDER_IP);
        Integer port = (Integer) context.getArgMap().get(RunningConstant.BUILDER_PORT);
        String user = (String) context.getArgMap().get(RunningConstant.BUILDER_REMOTE_USER);
        String password = (String) context.getArgMap().get(RunningConstant.BUILDER_REMOTE_PWD);
        String localPath = "/ci/build.sh";
        String remotePath = "/Users/yuanjinxiu/ci";
        try {
            // JSch登录
            Session session = createSession(user, host, port, password);
            session.connect();
            // SCP复制文件、授予执行权限并执行
            executeScpAndRunScript(session, localPath, remotePath);
            // 关闭会话
            session.disconnect();
        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private Session createSession(String user, String host, Integer port, String password) throws JSchException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        return session;
    }

    private void executeScpAndRunScript(Session session, String localPath, String remotePath)
            throws JSchException, IOException {
        try {
            // SCP复制文件
            copyFileWithScp(session, localPath, remotePath);
            // 授予执行权限
            grantExecutePermission(session, remotePath);
            // 执行脚本
            executeScript(session, remotePath);
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }

    private void copyFileWithScp(Session session, String localPath, String remotePath)
            throws JSchException, SftpException, IOException {
        Channel channel = session.openChannel("exec");
        ChannelExec channelExec = (ChannelExec) channel;

        String scpCommand = "scp -t " + remotePath;
        channelExec.setCommand(scpCommand);

        try (OutputStream outputStream = channelExec.getOutputStream();
             InputStream inputStream = channelExec.getInputStream()) {

            channelExec.connect();

            if (checkAck(inputStream) != 0) {
                throw new RuntimeException("SCP error");
            }

            // Send the command header "C0644 filesize filename"
            long filesize = new ClassPathResource(localPath).contentLength();
            String command = "C0644 " + filesize + " ";
            command += localPath.substring(localPath.lastIndexOf('/') + 1);
            command += "\n";
            outputStream.write(command.getBytes());
            outputStream.flush();

            if (checkAck(inputStream) != 0) {
                throw new RuntimeException("SCP error");
            }

            // Send the file content
            try (InputStream fileInputStream = new ClassPathResource(localPath).getInputStream()) {
                byte[] buffer = new byte[1024];
                int len;

                while ((len = fileInputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, len);
                }
            }

            // Send the file end marker
            byte[] eof = new byte[1];
            outputStream.write(eof);
            outputStream.flush();

            if (checkAck(inputStream) != 0) {
                throw new RuntimeException("SCP error");
            }
        } finally {
            channelExec.disconnect();
        }
    }

    private void grantExecutePermission(Session session, String remotePath) throws JSchException {
        Channel channel = session.openChannel("exec");
        ChannelExec channelExec = (ChannelExec) channel;

        String command = "chmod +x " + remotePath;
        channelExec.setCommand(command);

        try (InputStream inputStream = channelExec.getInputStream()) {
            channelExec.connect();

            // 读取命令执行的输出
            readCommandOutput(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            channelExec.disconnect();
        }
    }

    private void executeScript(Session session, String remotePath) throws JSchException {
        Channel channel = session.openChannel("exec");
        ChannelExec channelExec = (ChannelExec) channel;
        String command = remotePath;
        channelExec.setCommand(command);

        try (InputStream inputStream = channelExec.getInputStream()) {
            channelExec.connect();

            // 读取命令执行的输出
            readCommandOutput(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            channelExec.disconnect();
        }
    }

    private void readCommandOutput(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    private int checkAck(InputStream inputStream) throws IOException {
        // '0' 表示成功，'1' 表示错误
        return inputStream.read();
    }
}
