package com.rany.cake.devops.base.service.plugins.ci;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.service.base.Constants;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.RunningConstant;
import com.rany.cake.devops.base.service.plugins.annotation.PluginName;
import com.rany.cake.devops.base.service.utils.JSCHTool;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.util.SchedulerPools;
import com.rany.cake.toolkit.lang.constant.Letters;
import com.rany.cake.toolkit.lang.utils.Strings;
import com.rany.cake.toolkit.net.remote.channel.SessionStore;
import com.rany.cake.toolkit.net.remote.channel.ShellExecutor;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        String host = (String) context.getArgMap().get(RunningConstant.BUILDER_IP);
        Integer port = (Integer) context.getArgMap().get(RunningConstant.BUILDER_PORT);
        String user = (String) context.getArgMap().get(RunningConstant.BUILDER_REMOTE_USER);
        String password = (String) context.getArgMap().get(RunningConstant.BUILDER_REMOTE_PWD);
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
        try (SessionStore sessionStore = hostConnectionService.openSessionStore(deployHost)) {
            ShellExecutor executor = sessionStore.getShellExecutor();
            executor.connect();
            executor.scheduler(SchedulerPools.TERMINAL_SCHEDULER);
            executor.streamHandler(inputStream -> {
                byte[] bs = new byte[Const.BUFFER_KB_4];
                BufferedInputStream in = new BufferedInputStream(inputStream, Const.BUFFER_KB_4);
                int read;
                try {
                    while ((read = in.read(bs)) != -1) {
                        // 响应
                        try (ByteArrayOutputStream o = new ByteArrayOutputStream()) {
                            o.write(bs, 0, read);
                            log.info(o.toString());
                        }
                    }
                } catch (IOException ex) {
                    log.error("terminal 读取流失败", ex);
                }
            });


            executor.write(Strings.bytes("mkdir -p " + workspace));
            executor.write(new byte[]{Letters.LF});
        }

        JSch jsch = new JSch();
        Session session = null;
        try {
            log.info("connect: {}, {}, {}", user, host, port);
            session = jsch.getSession(user, host, port);
            session.setPassword(password);

            // 关闭 StrictHostKeyChecking，避免 UnknownHostKey 导致连接失败
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            // 连接到服务器
            session.connect();

            if (!JSCHTool.remoteExecute(session, "mkdir -p " + workspace)) {
                log.error("创建工作目录失败");
                return false;
            }
            if (!JSCHTool.remoteExecute(session, "cd " + workspace + ";" +
                    " curl -JLO https://github.com/WXzhongwang/cake-devops-base/releases/download/beta-v0.0.2/java-build-source.tar.gz; " +
                    " tar -zxvf java-build-source.tar.gz;" +
                    " chmod +x *.sh;")) {
                log.error("脚本下载失败");
                return false;
            }
        } catch (JSchException e) {
            log.error("WorkSpacePlugin error", e);
            return false;
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }
        return true;
    }

}
