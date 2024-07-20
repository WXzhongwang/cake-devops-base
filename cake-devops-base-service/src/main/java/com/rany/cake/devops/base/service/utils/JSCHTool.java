package com.rany.cake.devops.base.service.utils;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
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
            // InputStream input = channel.getInputStream();
            channel.connect();
//            try {
//                BufferedReader inputReader = new BufferedReader(new InputStreamReader(input));
//                String inputLine = null;
//                while ((inputLine = inputReader.readLine()) != null) {
//                    log.info("{}", inputLine);
//                }
//            } finally {
//                if (input != null) {
//                    try {
//                        input.close();
//                    } catch (Exception e) {
//                        log.error("JSch inputStream close error:", e);
//                    }
//                }
//            }
//
//            // 获取远程 Shell 的退出状态
//            int exitStatus = channel.getExitStatus();
//
//            // 根据退出状态判断是否执行成功
//            if (exitStatus == 0) {
//                log.info("Shell command executed successfully");
//                executionSuccess = true;
//            } else {
//                log.error("Shell command execution failed with exit status: {}", exitStatus);
//            }
            try (InputStream input = channel.getInputStream();
                 BufferedReader inputReader = new BufferedReader(new InputStreamReader(input))) {

                String line;
                while ((line = inputReader.readLine()) != null) {
                    log.info("Output: {}", line);
                }

                // 等待命令执行完毕
                while (!channel.isClosed()) {
                    Thread.sleep(100); // 等待100毫秒
                }

                int exitStatus = channel.getExitStatus();
                log.info("Command exited with status: {}", exitStatus);
                return exitStatus == 0;
            } catch (IOException | InterruptedException e) {
                log.error("Error executing command: {}", command, e);
                // throw new RemoteExecutionException("Failed to execute command", e);
            }
        }
//        catch (IOException e) {
//            log.error("IOException occur:", e);
//        }
        finally {
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
