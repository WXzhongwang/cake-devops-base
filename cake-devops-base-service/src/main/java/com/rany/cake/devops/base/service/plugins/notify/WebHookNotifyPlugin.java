package com.rany.cake.devops.base.service.plugins.notify;

import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.annotation.PluginName;
import org.springframework.stereotype.Component;

/**
 * 第三方webhook通知插件
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/19 18:27
 * @email 18668485565163.com
 */
@Component
@PluginName("WebHook通知")
public class WebHookNotifyPlugin extends BasePlugin {

    @Override
    public boolean init(DeployContext context) {

        return false;
    }

    @Override
    public boolean execute(DeployContext context) {
        return true;
    }
}
