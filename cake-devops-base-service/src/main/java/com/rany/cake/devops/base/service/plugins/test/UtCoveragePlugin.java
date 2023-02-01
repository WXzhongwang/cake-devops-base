package com.rany.cake.devops.base.service.plugins.test;

import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.context.Plugin;

/**
 * 单元覆盖
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/19 18:27
 * @email 18668485565163.com
 */
public abstract class UtCoveragePlugin implements Plugin {

    @Override
    public boolean execute(DeployContext context) {
        return true;
    }
}
