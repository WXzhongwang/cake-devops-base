package com.rany.cake.devops.base.service.plugins.notify;

import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.RunningConstant;

/**
 * 钉钉通知
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/19 18:27
 * @email 18668485565163.com
 */
public class DingtalkNotifyPlugin extends BasePlugin {

    @Override
    public boolean init(DeployContext context) {
        return false;
    }

    @Override
    public boolean execute(DeployContext context) {
        context.putArg(RunningConstant.DING_WEBHOOK_URL, "http://xxxxx?access_token=xxx");
        return true;
    }
}
