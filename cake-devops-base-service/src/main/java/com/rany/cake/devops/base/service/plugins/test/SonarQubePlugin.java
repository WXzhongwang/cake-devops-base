package com.rany.cake.devops.base.service.plugins.test;

import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.RunningConstant;
import org.springframework.stereotype.Component;

/**
 * sonar代码扫描
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/19 18:27
 * @email 18668485565163.com
 */
@Component
public class SonarQubePlugin extends BasePlugin {
    @Override
    public boolean init(DeployContext context) {
        return false;
    }

    @Override
    public boolean execute(DeployContext context) {
        this.putArg(RunningConstant.SONAR_ADDRESS_URL, "http://127.0.0.1:9000");
        this.putArg(RunningConstant.SONAR_LOGIN, "admin");
        this.putArg(RunningConstant.SONAR_PWD, "123456789");
        return true;
    }
}
