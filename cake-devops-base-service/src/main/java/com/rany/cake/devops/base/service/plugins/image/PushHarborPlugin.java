package com.rany.cake.devops.base.service.plugins.image;


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
 * 推送镜像到Harbor
 *
 * @author zhongshengwang
 * @description 推送镜像到Harbor
 * @date 2023/1/20 19:41
 * @email 18668485565163.com
 */

@Component
@PluginName("推送镜像到Harbor")
public class PushHarborPlugin extends BasePlugin {
    @Override
    public boolean init(DeployContext context) {
        return true;
    }

    @Override
    public boolean execute(DeployContext context) {
        String webHook = context.getApp().getWebhook();
        String repo = context.getApp().getCodeRepository().getRepo();
        String appName = context.getApp().getAppName().getName();
        String releaseVersion = context.getRelease().getReleaseNo();

        String workspace = (String) context.getArgMap().get(RunningConstant.WORKSPACE_HOME);
        log.info("workspace directory: " + workspace);

        try {
            SessionStore sessionStore = getCurrentSessionStore(context);
            Session session = sessionStore.getSession();

            // JSCHTool.remoteExecute(session, "cd " + workspace);
            // push_harbor_image "$1" "$2" "$3" "$4" "$5"
//            local repo_url=$1
//            local namespace=$2
//            local project=$3
//            local version=$4
//            local webhook_url=$5
            //String executeCommand = String.join(" ", "sh", "push_harbor.sh", repo, appName, appName, releaseVersion, webHook);
            String executeCommand = String.format(" sh push_harbor.sh '%s' %s %s %s '%s'", repo, appName, appName, releaseVersion, webHook);
            if (!JSCHTool.remoteExecute(session, "cd " + workspace + "; " + executeCommand)) {
                log.error("推送harbor失败");
                return false;
            }
            String imageName = String.format("%s/%s/%s:%s",
                    crConfig.getHost(), crConfig.getNameSpace(), appName, releaseVersion);
            log.info("完整镜像地址，{}", imageName);
        } catch (JSchException e) {
            log.error("PushHarborPlugin error", e);
            return false;
        }
        return true;
    }
}
