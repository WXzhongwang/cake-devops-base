package com.rany.cake.devops.base.service.plugins;

import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.context.Plugin;

/**
 * jenkins构建
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/19 18:23
 * @email 18668485565163.com
 */
public class JenkinsBuildPlugin implements Plugin {

    @Override
    public boolean execute(DeployContext context) {
        return true;
    }
}