package com.rany.cake.devops.base.service.plugins.builds;

import com.rany.cake.devops.base.domain.enums.DeployStageEnum;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;

/**
 * Java构建
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/19 18:23
 * @email 18668485565163.com
 */
public class JavaBuildPlugin extends BasePlugin {
    private String jdkVersion;
    private String mvnVersion;
    private String run;
    private String env;
    private static final String DEFAULT_SHELL_CMD = "mvn clean package -U -Dmaven.compile.fork=true -P${ENV}";

    @Override
    public boolean init(DeployContext context) {
        context.setStage(DeployStageEnum.BUILD);
        this.env = context.getAppEnv().getEnv().name();
        return false;
    }

    @Override
    public boolean execute(DeployContext context) {
        return true;
    }

}
