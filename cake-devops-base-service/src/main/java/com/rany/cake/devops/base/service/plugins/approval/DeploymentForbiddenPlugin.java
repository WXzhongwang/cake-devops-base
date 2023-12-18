package com.rany.cake.devops.base.service.plugins.approval;

import com.rany.cake.devops.base.domain.enums.AppEnvEnum;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.RunningConstant;
import org.springframework.stereotype.Component;

import java.util.Objects;

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
        context.putArg(RunningConstant.FORBIDDEN_CHECK_REQUIRED, Boolean.FALSE);
        if (Objects.equals(context.getAppEnv().getEnv(), AppEnvEnum.PROD)) {
            context.putArg(RunningConstant.FORBIDDEN_CHECK_REQUIRED, Boolean.TRUE);
            context.putArg(RunningConstant.FORBIDDEN_CHECK_ADDRESS,
                    String.format(RunningConstant.FORBIDDEN_CURL_ADDRESS, appId));
        }
        if (context.getAppEnv().getEnv() != AppEnvEnum.PROD) {

        }
        return true;
    }
}