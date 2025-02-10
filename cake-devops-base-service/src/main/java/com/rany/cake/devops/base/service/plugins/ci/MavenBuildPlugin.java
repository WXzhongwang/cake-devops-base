package com.rany.cake.devops.base.service.plugins.ci;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.rany.cake.devops.base.api.dto.SystemEnvDTO;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.RunningConstant;
import com.rany.cake.devops.base.service.plugins.annotation.PluginName;
import com.rany.cake.devops.base.service.utils.JSCHTool;
import com.rany.cake.toolkit.net.remote.channel.SessionStore;
import com.rany.cake.toolkit.net.remote.channel.SftpExecutor;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * maven 构建
 *
 * @author zhongshengwang
 * @description maven 构建
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
        String mavenVersion = context.getApp().getAppExtend().getMavenVersion();
        String jdkVersion = context.getApp().getAppExtend().getJdkVersion();
        String repo = context.getApp().getCodeRepository().getRepo();
        /**
         * maven自定义编译脚本
         * update app_env set custom_build_script = 'mvn clean package -Ptest -U -DskipTests'
         * maven clean package -Ptest -U -DskipTests
         * */
        String customBuildScript = context.getAppEnv().getCustomBuildScript();

        String workspace = (String) context.getArgMap().get(RunningConstant.WORKSPACE_HOME);
        log.info("workspace directory: " + workspace);
        SystemEnvDTO systemEnv = systemEnvService.getSystemEnv("maven_settings_global_config");
        String localSettingsXmlContent = systemEnv.getAttrValue();
        String charset = hostDomainService.getSftpCharset(context.getHost().getHostId().getHostId());
        SessionStore sessionStore = getCurrentSessionStore(context);
        SftpExecutor currentSftpExecutor = sessionStore.getSftpExecutor(charset);
        try {
            Session session = sessionStore.getSession();

//            local repo_url=$1
//            local webhook_url=$2
//            local custom_script=$3
//            local maven_version=$4

            // 使用 cat 命令将文件内容写入远程文件
//            String command = String.format("cat > %s <<EOF\n%s\nEOF", "~/.m2/settings.xml", localSettingsXmlContent);
            // String command = String.format("echo \"%s\" > %s", localSettingsXmlContent, "~/.m2/settings.xml");
            // 获取charset
            currentSftpExecutor.connect();
            String settingFilePath = currentSftpExecutor.getHome() + "/.m2/settings.xml";
            try {
                boolean exist = currentSftpExecutor.isExist(settingFilePath);
                if (!exist) {
                    currentSftpExecutor.write(settingFilePath, localSettingsXmlContent.getBytes());
                }
            } catch (IOException e) {
                log.error("拷贝maven settings文件 命令执行失败", e);
                return false;
            }

            String executeCommand = String.format(" sh maven_build.sh '%s' '%s' '%s' '%s' '%s'",
                    customBuildScript, mavenVersion, jdkVersion, repo, webHook
            );
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
