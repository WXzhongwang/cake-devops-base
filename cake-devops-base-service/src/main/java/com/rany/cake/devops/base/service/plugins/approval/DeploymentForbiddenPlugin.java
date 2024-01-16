package com.rany.cake.devops.base.service.plugins.approval;

import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import org.springframework.stereotype.Component;

/**
 * 封网插件
 *
 * @author zhongshengwang
 * @description 封网插件
 * @date 2023/1/20 20:30
 * @email 18668485565163.com
 */
@Component
public class DeploymentForbiddenPlugin extends BasePlugin {

    @Override
    public boolean init(DeployContext context) {
        return true;
    }

    @Override
    public boolean execute(DeployContext context) {
        String appId = context.getApp().getAppId().getAppId();
        // 线上应用处理存在封网校验
        return true;
    }
}