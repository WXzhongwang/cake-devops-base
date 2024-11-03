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
 * 推送镜像到阿里云
 *
 * @author zhongshengwang
 * @description 推送镜像到阿里云
 * @date 2023/1/20 19:41
 * @email 18668485565163.com
 */

@Component
@PluginName("推送镜像到阿里云")
public class PushAcrPlugin extends BasePlugin {
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
            // push_aliyun_image "$1" "$2" "$3" "$4" "$5"
//            local repo_url=$1
//            local namespace=$2
//            local project=$3
//            local version=$4
//            local webhook_url=$5
            // String executeCommand = String.join(" ", "sh", "push_aliyun.sh", repo, appName, appName, releaseVersion, webHook);
            String executeCommand = String.format(" sh %s '%s' %s %s %s '%s'",
                    crConfig.getShellName(),
                    repo, crConfig.getNameSpace(), appName, releaseVersion, webHook);
            if (!JSCHTool.remoteExecute(session, "cd " + workspace + "; " + executeCommand)) {
                log.error("推送阿里云失败");
                return false;
            }
            String imageName = String.format("%s/%s/%s:%s",
                    crConfig.getHost(), crConfig.getNameSpace(), appName, releaseVersion);
            log.info("完整镜像地址，{}", imageName);
            context.setDeploymentImage(imageName);
            context.getDeployHistory().setImagePath(imageName);
        } catch (JSchException e) {
            log.error("DeliveryPlugin error", e);
            return false;
        }
        return true;
    }
}
