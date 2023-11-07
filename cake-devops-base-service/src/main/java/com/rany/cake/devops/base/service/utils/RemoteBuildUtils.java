package com.rany.cake.devops.base.service.utils;

/**
 * RemoteBuild
 *
 * @date 2023/11/7
 * @slogan Why Not
 */

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RemoteBuildUtils {
    private String host;
    private int port;
    private String username;
    private String password;

    public RemoteBuildUtils(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public void executeCommand(String command) {
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);

            channel.connect();

            InputStream in = channel.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            channel.disconnect();
            session.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String host = "xxx";
        String username = "xxx";
        String password = "xxx";
        int port = 22;
        String command = "scp /Users/xxx/workspace/test/src/main/java/local-script.sh xxx@U-xxx-0145.local:/Users/xxx/";
        RemoteBuildUtils remoteCI = new RemoteBuildUtils(host, port, username, password);
        remoteCI.executeCommand(command);
    }
}