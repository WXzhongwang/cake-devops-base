package com.rany.cake.devops.base.service.plugins.test;

import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import org.springframework.stereotype.Component;

/**
 * 单元覆盖
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/19 18:27
 * @email 18668485565163.com
 */
@Component
public class UtCoveragePlugin extends BasePlugin {
    @Override
    public boolean init(DeployContext context) {
        return false;
    }

    @Override
    public boolean execute(DeployContext context) {
        return true;
    }
}
