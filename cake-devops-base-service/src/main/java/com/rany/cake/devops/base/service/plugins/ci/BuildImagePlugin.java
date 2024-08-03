package com.rany.cake.devops.base.service.plugins.ci;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.RunningConstant;
import com.rany.cake.devops.base.service.plugins.annotation.PluginName;
import com.rany.cake.devops.base.service.utils.JSCHTool;
import com.rany.cake.toolkit.net.remote.channel.SessionStore;
import org.springframework.stereotype.Component;

/**
 * 构建镜像
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/20 19:41
 * @email 18668485565163.com
 */

@Component
@PluginName("镜像构建")
public class BuildImagePlugin extends BasePlugin {
    @Override
    public boolean init(DeployContext context) {
        return true;
    }

    @Override
    public boolean execute(DeployContext context) {
        String webHook = context.getApp().getWebhook();
        String repo = context.getApp().getCodeRepository().getRepo();
        String appName = context.getApp().getAppName().getName();
        String envFlag = context.getAppEnv().getEnv().name().toLowerCase();
        String releaseNo = context.getRelease().getReleaseNo();

        String workspace = (String) context.getArgMap().get(RunningConstant.WORKSPACE_HOME);
        log.info("workspace directory: " + workspace);

        try {
            SessionStore sessionStore = getCurrentSessionStore(context);
            Session session = sessionStore.getSession();
            // JSCHTool.remoteExecute(session, "cd " + workspace);
//            build_image "$1" "$2" "$3" "$4"
//            local repo_url=$1
//            local project=$2
//            local version=$3
//            local webhook_url=$4
//            local env=$5
            //String executeCommand = String.join(" ", "sh", "build_image.sh", repo, appName, releaseVersion, webHook);
            String executeCommand = String.format(" sh build_image.sh '%s' %s %s '%s' '%s'", repo, appName, releaseNo, webHook, envFlag);
            if (!JSCHTool.remoteExecute(session, "cd " + workspace + "; " +
                    executeCommand)) {
                log.error("镜像构架失败");
                return false;
            }
        } catch (JSchException e) {
            log.error("BuildImagePlugin error", e);
            return false;
        }
        return true;
    }
}
