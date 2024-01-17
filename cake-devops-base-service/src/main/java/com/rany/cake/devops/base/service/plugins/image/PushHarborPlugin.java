package com.rany.cake.devops.base.service.plugins.image;


import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.RunningConstant;
import com.rany.cake.devops.base.service.utils.JSCHTool;
import org.springframework.stereotype.Component;

/**
 * 推送镜像到Harbor
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/20 19:41
 * @email 18668485565163.com
 */

@Component
public class PushHarborPlugin extends BasePlugin {
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
        String webHook = context.getApp().getWebhook();
        String repo = context.getApp().getCodeRepository().getRepo();
        String appName = context.getApp().getAppName().getName();
        String releaseVersion = context.getRelease().getReleaseNo();

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

            // JSCHTool.remoteExecute(session, "cd " + workspace);
            // push_harbor_image "$1" "$2" "$3" "$4" "$5"
//            local repo_url=$1
//            local namespace=$2
//            local project=$3
//            local version=$4
//            local webhook_url=$5
            //String executeCommand = String.join(" ", "sh", "push_harbor.sh", repo, appName, appName, releaseVersion, webHook);
            String executeCommand = String.format(" sh push_harbor.sh '%s' %s %s %s '%s'", repo, appName, appName, releaseVersion, webHook);
            JSCHTool.remoteExecute(session, executeCommand);
        } catch (JSchException e) {
            log.error("PushHarborPlugin error", e);
            return false;
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }
        return true;
    }
}
