package com.rany.cake.devops.base.service.plugins.approval;

import com.rany.cake.devops.base.domain.enums.AppEnvEnum;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.RunningConstant;
import org.springframework.stereotype.Component;

/**
 * 发布审批插件
 * 通过curl 特定接口，判断应用发布审批是否通过
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/20 19:41
 * @email 18668485565163.com
 */
@Component
public class ApprovalPlugin extends BasePlugin {

    @Override
    public boolean init(DeployContext context) {
        return false;
    }

    @Override
    public boolean execute(DeployContext context) {
        this.putArg(RunningConstant.APPROVAL_CHECK_REQUIRED, Boolean.FALSE);
        if (context.getAppEnv().equals(AppEnvEnum.PROD)) {
            this.putArg(RunningConstant.APPROVAL_CHECK_REQUIRED, Boolean.TRUE);
            this.putArg(RunningConstant.APPROVAL_CHECK_ADDRESS,
                    String.format(RunningConstant.APPROVAL_CURL_ADDRESS,
                            context.getApp().getId().getId()));
        }
        return true;
    }
}
