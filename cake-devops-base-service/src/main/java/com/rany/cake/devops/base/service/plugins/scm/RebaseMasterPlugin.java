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
 * 合并主干
 *
 * @author zhongshengwang
 * @description 代码分支
 * @date 2023/1/20 20:30
 * @email 18668485565163.com
 */
@Component
@PluginName("合并主干")
public class RebaseMasterPlugin extends BasePlugin {
    @Override
    public boolean init(DeployContext context) {
        return true;
    }

    @Override
    public boolean execute(DeployContext context) {
        String workspace = (String) context.getArgMap().get(RunningConstant.WORKSPACE_HOME);
        log.info("workspace directory: " + workspace);

        Host deployHost = context.getHost();
        try (SessionStore sessionStore = hostConnectionService.openSessionStore(deployHost)) {
            Session session = sessionStore.getSession();
            String executeCommand = String.format("cd " + workspace + "; git fetch origin && git rebase origin/master");
            try {
                RETRYER.call(() -> {
                    if (!JSCHTool.remoteExecute(session, "cd " + workspace + "; " + executeCommand)) {
                        log.error("合并主干失败");
                        return false;
                    }
                    return true;
                });
            } catch (UncheckedExecutionException | ExecutionException | RetryException e) {
                Throwable cause = e.getCause();
                if (cause instanceof JSchException) {
                    log.error("JSchException during retry attempt", cause);
                    log.error("RebaseMasterPlugin error", e);
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
