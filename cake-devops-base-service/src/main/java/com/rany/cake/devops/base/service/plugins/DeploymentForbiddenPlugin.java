package com.rany.cake.devops.base.service.plugins;

import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.context.Plugin;

/**
 * 封网插件
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/20 20:30
 * @email 18668485565163.com
 */
public class DeploymentForbiddenPlugin implements Plugin {

    @Override
    public boolean execute(DeployContext context) {
        return true;
    }
}