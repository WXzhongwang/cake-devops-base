package com.rany.cake.devops.base.service.plugins.builds;

import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;

/**
 * jenkins构建
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/19 18:23
 * @email 18668485565163.com
 */
public class PythonBuildPlugin extends BasePlugin {

    @Override
    public boolean init(DeployContext context) {
        return false;
    }

    @Override
    public boolean execute(DeployContext context) {
        return true;
    }


    @Override
    public boolean stopWhenFailure() {
        return true;
    }
}
