package com.rany.cake.devops.base.service.plugins.scm;

import com.github.rholder.retry.RetryException;
import com.google.common.util.concurrent.UncheckedExecutionException;
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

import java.util.concurrent.ExecutionException;

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
        String webHook = context.getApp().getWebhook();
        String repo = context.getApp().getCodeRepository().getRepo();
        String branch = context.getRelease().getReleaseBranch();

        String workspace = (String) context.getArgMap().get(RunningConstant.WORKSPACE_HOME);
        log.info("workspace directory: " + workspace);

        Host deployHost = context.getHost();
        try (SessionStore sessionStore = hostConnectionService.openSessionStore(deployHost)) {
            Session session = sessionStore.getSession();
            // JSCHTool.remoteExecute(session, "cd " + workspace);
            //    checkout "$1" "$2" "$3" "$4"
            //    local repo_url=$1
            //    local branch_name=$2
            //    local folder_name=$3
            //    local webhook_url=$4
            String executeCommand = String.format(" sh checkout.sh '%s' %s '%s'", repo, branch, webHook);
            try {
                RETRYER.call(() -> {
                    if (!JSCHTool.remoteExecute(session, "cd " + workspace + "; " + executeCommand)) {
                        log.error("代码拉取失败");
                        return false;
                    }
                    return true;
                });
            } catch (UncheckedExecutionException | ExecutionException | RetryException e) {
                Throwable cause = e.getCause();
                if (cause instanceof JSchException) {
                    log.error("JSchException during retry attempt", cause);
                    log.error("CodePlugin error", e);
                    return false;
                } else {
                    log.error("Unexpected error during retry attempt", cause);
                }
                return false;
            }
        }
        return true;
    }
}
