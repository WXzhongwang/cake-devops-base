package com.rany.cake.devops.base.service.plugins.test;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.RunningConstant;
import com.rany.cake.devops.base.service.utils.JSCHTool;
import org.springframework.stereotype.Component;

/**
 * sonar代码扫描
 *
 * @author zhongshengwang
 * @description sonar代码扫描
 * @date 2023/1/19 18:27
 * @email 18668485565163.com
 */
@Component
public class SonarQubePlugin extends BasePlugin {

    @Override
    public boolean init(DeployContext context) {
        return true;
    }

    @Override
    public boolean execute(DeployContext context) {
        context.putArg(RunningConstant.SONAR_ADDRESS_URL, "http://127.0.0.1:9000");
        context.putArg(RunningConstant.SONAR_LOGIN, "admin");
        context.putArg(RunningConstant.SONAR_PWD, "123456789");

        String host = (String) context.getArgMap().get(RunningConstant.BUILDER_IP);
        Integer port = (Integer) context.getArgMap().get(RunningConstant.BUILDER_PORT);
        String user = (String) context.getArgMap().get(RunningConstant.BUILDER_REMOTE_USER);
        String password = (String) context.getArgMap().get(RunningConstant.BUILDER_REMOTE_PWD);
        String webHook = context.getApp().getWebhook();
        String repo = context.getApp().getCodeRepository().getRepo();

        String workspace = (String) context.getArgMap().get(RunningConstant.WORKSPACE_HOME);
        log.info("workspace directory: " + workspace);

        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession(user, host, port);
            session.setPassword(password);

            // 关闭 StrictHostKeyChecking，避免 UnknownHostKey 导致连接失败
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            // 连接到服务器
            session.connect();
            //JSCHTool.remoteExecute(session, "cd " + workspace);
//            sonar_scan "$1" "$2"
//            local repo_url=$1
//            local webhook_url=$2
            String executeCommand = String.format(" sh sonar_scan.sh '%s'  '%s'", repo, webHook);
            JSCHTool.remoteExecute(session, "cd " + workspace + "; " + executeCommand);
        } catch (JSchException e) {
            log.error("SonarQubePlugin error", e);
            return false;
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }
        return true;
    }
}
