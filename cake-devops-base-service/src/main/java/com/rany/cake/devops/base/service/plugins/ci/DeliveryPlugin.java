package com.rany.cake.devops.base.service.plugins.ci;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.RunningConstant;
import com.rany.cake.devops.base.service.ssh.JSCHTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 推送脚本并执行构建指令
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/20 19:41
 * @email 18668485565163.com
 */

@Slf4j
@Component
public class DeliveryPlugin extends BasePlugin {
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
        String remoteBase = "/Users/yuanjinxiu/ci/";
        String remoteWorkSpaceFolder = context.getApp().getAppName().getName();
        String repo = context.getApp().getCodeRepository().getRepo();
        String branch = context.getRelease().getReleaseBranch();

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

            JSCHTool.remoteExecute(session, "ls -l");
            JSCHTool.remoteExecute(session, "ls -l");
            JSCHTool.remoteExecute(session, "pwd");
            JSCHTool.remoteExecute(session, "mkdir " + remoteBase + remoteWorkSpaceFolder);
            // J：此选项告诉 -O, --remote-name 选项使用服务器指定的 Content-Disposition 文件名，而不是从 URL 中提取文件名。
            // L：如果服务器报告请求的页面已移动到不同的位置（用 Location: 标头和 3XX 响应代码指示），此选项将使 curl 在新位置重做请求。
            // O：使用此选项，您无需指定下载的输出文件名。
            String executeCommand = String.join(" ", "sh", "build.sh", repo, branch, "false", "cake-devops-base:v1.0.0", "\"https://oapi.dingtalk.com/robot/send?access_token=89ca235dbe9f617f4ca045a1f24b0e61a32e9f845771752416377089c36470b7\"");
            JSCHTool.remoteExecute(session, "cd " + remoteBase + remoteWorkSpaceFolder + ";\n" +
                    "curl -JLO https://github.com/WXzhongwang/cake-devops-base/releases/download/beta-v0.0.1/java-build-source.tar.gz;" +
                    "tar -zxvf java-build-source.tar.gz;\n" +
                    "chmod +x build.sh;\n" +
                    executeCommand);
        } catch (JSchException e) {
            log.error("DeliveryPlugin error", e);
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }
        return true;
    }
}
