package com.rany.cake.devops.base.service.ssh;

import com.jcraft.jsch.*;

public class JshTests {

    public static void main(String[] args) {
        String host = "your_server_ip";
        String user = "your_username";
        String password = "your_password";
        int port = 22;

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);

            // 关闭 StrictHostKeyChecking，避免 UnknownHostKey 导致连接失败
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            // 连接到服务器
            session.connect();

            // 执行 shell 命令
            String command = "ls -l";
            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);

            // 获取执行命令的输出流
            java.io.InputStream in = channel.getInputStream();

            // 连接 channel
            channel.connect();

            // 读取命令输出
            byte[] buffer = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int bytesRead = in.read(buffer, 0, 1024);
                    if (bytesRead < 0) break;
                    System.out.print(new String(buffer, 0, bytesRead));
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) continue;
                    System.out.println("Exit status: " + channel.getExitStatus());
                    break;
                }
            }

            // 关闭连接
            channel.disconnect();
            session.disconnect();

        } catch (JSchException | java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
