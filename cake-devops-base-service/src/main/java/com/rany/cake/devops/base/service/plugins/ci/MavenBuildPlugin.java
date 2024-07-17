package com.rany.cake.devops.base.service.plugins.ci;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.RunningConstant;
import com.rany.cake.devops.base.service.plugins.annotation.PluginName;
import com.rany.cake.devops.base.service.utils.JSCHTool;
import com.rany.cake.toolkit.net.remote.channel.SessionStore;
import org.springframework.stereotype.Component;

/**
 * maven 构建
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/20 19:41
 * @email 18668485565163.com
 */

@Component
@PluginName("Maven构建")
public class MavenBuildPlugin extends BasePlugin {
    @Override
    public boolean init(DeployContext context) {
        return true;
    }

    @Override
    public boolean execute(DeployContext context) {
        String webHook = context.getApp().getWebhook();
        String repo = context.getApp().getCodeRepository().getRepo();

        String workspace = (String) context.getArgMap().get(RunningConstant.WORKSPACE_HOME);
        log.info("workspace directory: " + workspace);

        Host deployHost = context.getHost();
        try (SessionStore sessionStore = hostConnectionService.openSessionStore(deployHost)) {
            Session session = sessionStore.getSession();
//            mvn_build "$1" "$2"
//            local repo_url=$1
//            local webhook_url=$2
            //String executeCommand = String.join(" ", "sh", "maven_build.sh", repo, webHook);
            String executeCommand = String.format(" sh maven_build.sh '%s' '%s'", repo, webHook);
            if (!JSCHTool.remoteExecute(session, "cd " + workspace + "; " + executeCommand)) {
                log.error("maven编译打包失败");
                return false;
            }
        } catch (JSchException e) {
            log.error("MavenBuildPlugin error", e);
            return false;
        }
        return true;
    }
}
