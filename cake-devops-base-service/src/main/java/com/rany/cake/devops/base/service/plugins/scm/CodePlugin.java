package com.rany.cake.devops.base.service.plugins.scm;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.RunningConstant;
import com.rany.cake.devops.base.service.plugins.annotation.PluginName;
import com.rany.cake.devops.base.service.utils.JSCHTool;
import org.springframework.stereotype.Component;

/**
 * 拉取代码插件
 *
 * @author zhongshengwang
 * @description 拉取代码插件
 * @date 2023/1/20 19:41
 * @email 18668485565163.com
 */

@Component
@PluginName("拉取代码")
public class CodePlugin extends BasePlugin {
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
        String branch = context.getRelease().getReleaseBranch();

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
            //    checkout "$1" "$2" "$3" "$4"
            //    local repo_url=$1
            //    local branch_name=$2
            //    local folder_name=$3
            //    local webhook_url=$4
            String executeCommand = String.format(" sh checkout.sh '%s' %s '%s'", repo, branch, webHook);
            if (!JSCHTool.remoteExecute(session, "cd " + workspace + "; " + executeCommand)) {
                log.error("代码拉取失败");
                return false;
            }
        } catch (JSchException e) {
            log.error("CodePlugin error", e);
            return false;
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }
        return true;
    }
}
