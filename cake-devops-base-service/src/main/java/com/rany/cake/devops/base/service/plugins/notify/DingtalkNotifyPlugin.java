package com.rany.cake.devops.base.service.plugins.notify;

import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.context.Plugin;

/**
 * 邮件通知插件
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/19 18:27
 * @email 18668485565163.com
 */
public class DingtalkNotifyPlugin implements Plugin {

    @Override
    public boolean execute(DeployContext context) {
        return true;
    }
}
