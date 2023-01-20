package com.rany.cake.devops.base.service.plugins;

import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.context.Plugin;

/**
 * 发布审批插件
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/20 19:41
 * @email 18668485565163.com
 */
public class ApprovalPlugin implements Plugin {

    @Override
    public boolean execute(DeployContext context) {
        return true;
    }
}
