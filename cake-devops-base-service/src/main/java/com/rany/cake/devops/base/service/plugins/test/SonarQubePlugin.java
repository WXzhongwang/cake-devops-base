package com.rany.cake.devops.base.service.plugins.test;

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
 * sonar代码扫描
 *
 * @author zhongshengwang
 * @description sonar代码扫描
 * @date 2023/1/19 18:27
 * @email 18668485565163.com
 */
@Component
@PluginName("sonar代码扫描")
public class SonarQubePlugin extends BasePlugin {

    @Override
    public boolean init(DeployContext context) {
        return true;
    }

    @Override
    public boolean execute(DeployContext context) {
        context.putArg(RunningConstant.SONAR_ADDRESS_URL, "http://127.0.0.1:9000");
        context.putArg(RunningConstant.SONAR_LOGIN, "admin");
        context.putArg(RunningConstant.SONAR_PWD, "123456789");

        String webHook = context.getApp().getWebhook();
        String repo = context.getApp().getCodeRepository().getRepo();

        String workspace = (String) context.getArgMap().get(RunningConstant.WORKSPACE_HOME);
        log.info("workspace directory: " + workspace);

        try {
            SessionStore sessionStore = getCurrentSessionStore(context);
            Session session = sessionStore.getSession();
            //JSCHTool.remoteExecute(session, "cd " + workspace);
//            sonar_scan "$1" "$2"
//            local repo_url=$1
//            local webhook_url=$2
            String executeCommand = String.format(" sh sonar_scan.sh '%s'  '%s'", repo, webHook);
            if (!JSCHTool.remoteExecute(session, "cd " + workspace + "; " + executeCommand)) {
                log.error("执行SonarScan失败");
                return false;
            }
        } catch (JSchException e) {
            log.error("SonarQubePlugin error", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean stopWhenFailure() {
        return false;
    }
}
