package com.rany.cake.devops.base.service.plugins.ci;

import com.github.rholder.retry.RetryException;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.valueobject.AppExtend;
import com.rany.cake.devops.base.service.base.Constants;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.RunningConstant;
import com.rany.cake.devops.base.service.plugins.annotation.PluginName;
import com.rany.cake.devops.base.service.utils.JSCHTool;
import com.rany.cake.toolkit.net.remote.channel.SessionStore;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;

/**
 * 工作空间创建
 *
 * @author zhongshengwang
 * @description 创建工作空间
 * @date 2023/1/20 19:41
 * @email 18668485565163.com
 */

@Component
@PluginName("创建工作空间")
public class WorkSpacePlugin extends BasePlugin {
    @Override
    public boolean init(DeployContext context) {
        return true;
    }

    @Override
    public boolean execute(DeployContext context) {
        String appName = context.getApp().getAppName().getName();
        // 获取当前日期和时间
        LocalDateTime now = LocalDateTime.now();
        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        // 格式化日期和时间
        String formattedDateTime = now.format(formatter);
        String workspace = String.format(Constants.REMOTE_BASE + "/" + appName + "/" + formattedDateTime);
        log.info("workspace directory: " + workspace);
        context.getArgMap().put(RunningConstant.WORKSPACE_HOME, workspace);

        Host deployHost = hostDomainService.getHost(new HostId(context.getHostId()));
        context.setHost(deployHost);

        try {

            SessionStore sessionStore = getCurrentSessionStore(context);
            Session session = sessionStore.getSession();


            RETRYER.call(() -> {
                if (!JSCHTool.remoteExecute(session, "mkdir -p " + workspace)) {
                    log.error("创建工作目录失败");
                    return false;
                }
                return true;
            });
//            local jdk_version=$1
//            local maven_version=$2
//            local node_version=$3
//            local go_version=$4
            AppExtend appExtend = context.getApp().getAppExtend();

            RETRYER.call(() -> {
                if (!JSCHTool.remoteExecute(session, String.format("cd %s;" +
                                        " curl -JLO https://rany-ops.oss-cn-hangzhou.aliyuncs.com/ci/0.0.10/java-build-source.tar.gz; " +
                                        " tar -zxvf java-build-source.tar.gz ;" +
                                        " chmod +x *.sh; " +
                                        " sh workspace_prepare.sh '%s' '%s' '%s' '%s'", workspace,
                                appExtend.getJdkVersion(),
                                appExtend.getMavenVersion(),
                                appExtend.getNodejsVersion(),
                                appExtend.getGoVersion()
                        )
                )) {
                    log.error("脚本下载失败");
                    return false;
                }
                return true;
            });

            // 拷贝 SSH 密钥到目标机器
//            RETRYER.call(() -> {
//                if (!JSCHTool.remoteExecute(session, String.format("mkdir -p ~/.ssh && cat >> ~/.ssh/authorized_keys <<EOF\n%s\nEOF",
//                        appExtend.getPublicKey()
//                ))) {
//                    log.error("拷贝 SSH 密钥失败");
//                    return false;
//                }
//                return true;
//            });


        } catch (UncheckedExecutionException | ExecutionException | RetryException e) {
            Throwable cause = e.getCause();
            if (cause instanceof JSchException) {
                log.error("JSchException during retry attempt", cause);
                log.error("WorkSpacePlugin error", e);
                return false;
            } else {
                log.error("Unexpected error during retry attempt", cause);
            }
            return false;
        }
        return true;
    }

}
