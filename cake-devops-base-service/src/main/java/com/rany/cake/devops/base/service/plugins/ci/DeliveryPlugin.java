package com.rany.cake.devops.base.service.plugins.ci;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.RunningConstant;
import com.rany.cake.devops.base.service.ssh.JSCHTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
        String remoteBase = "/Users/yuanjinxiu/ci/";
        String remoteWorkSpaceFolder = context.getApp().getAppName().getName();

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

            JSCHTool.remoteExecute(session, "ls -l");
            JSCHTool.remoteExecute(session, "ls -l");
            JSCHTool.remoteExecute(session, "pwd");
            JSCHTool.remoteExecute(session, "mkdir " + remoteBase + remoteWorkSpaceFolder);
            JSCHTool.remoteExecute(session, "cd " + remoteBase + remoteWorkSpaceFolder + ";curl -O ");

        } catch (JSchException e) {
            log.error("DeliveryPlugin error", e);
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }
        return true;
    }
}
