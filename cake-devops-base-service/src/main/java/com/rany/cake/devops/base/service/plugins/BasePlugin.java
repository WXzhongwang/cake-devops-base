package com.rany.cake.devops.base.service.plugins;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.rany.cake.devops.base.api.service.SystemEnvService;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.base.CrConfig;
import com.rany.cake.devops.base.domain.service.HostDomainService;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.context.Plugin;
import com.rany.cake.devops.base.service.handler.host.HostConnectionService;
import com.rany.cake.devops.base.service.utils.JSCHTool;
import com.rany.cake.toolkit.net.remote.channel.SessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 基础插件
 *
 * @author zhongshengwang
 * @description 基础插件
 * @date 2023/2/1 19:48
 * @email 18668485565163.com
 */
public abstract class BasePlugin implements Plugin {
    @Resource
    protected HostDomainService hostDomainService;
    @Resource
    protected SystemEnvService systemEnvService;

    @Resource
    protected HostConnectionService hostConnectionService;
    @Resource
    protected CrConfig crConfig;

    protected static final Logger log = LoggerFactory.getLogger("RabbitMq");

    /**
     * 重试器，3次，5s间隔
     */
    protected static final Retryer<Boolean> RETRYER = RetryerBuilder.<Boolean>newBuilder()
            .retryIfResult(result -> !result)
            .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
            .withStopStrategy(StopStrategies.stopAfterAttempt(3))
            .build();


    protected SessionStore getCurrentSessionStore(DeployContext context) {
        if (context.getSessionStore() != null) {
            return context.getSessionStore();
        }
        Host deployHost = context.getHost();
        SessionStore sessionStore = hostConnectionService.openSessionStore(deployHost);
        context.setSessionStore(sessionStore);
        return sessionStore;
    }

    protected void sendFailureNotification(DeployContext context, String comment) {
        String workspace = (String) context.getArgMap().get(RunningConstant.WORKSPACE_HOME);
        log.info("Send notification");
        log.info("workspace directory: " + workspace);

        SessionStore sessionStore = getCurrentSessionStore(context);
        try {
            Session session = sessionStore.getSession();
            String webHook = context.getApp().getWebhook();
            String appName = context.getApp().getAppName().getName();
            // "$message" "$webhook_url" "$status" "$app_name"
            String executeCommand = String.format(" sh send_notification.sh '%s' '%s' '%s' '%s'",
                    comment, webHook, "failed", appName);
            if (!JSCHTool.remoteExecute(session, "cd " + workspace + "; " + executeCommand)) {
                log.error("发送告警提醒");
            }
        } catch (JSchException e) {
            log.error("发送告警提醒 error", e);
        }
    }
}
