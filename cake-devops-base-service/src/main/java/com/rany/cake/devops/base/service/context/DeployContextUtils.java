package com.rany.cake.devops.base.service.context;

/**
 * ThreadLocal DeployContext
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 20:56
 * @email 18668485565163.com
 */
public class DeployContextUtils {

    private static final InheritableThreadLocal<DeployContext> DEPLOY_CONTEXT = new InheritableThreadLocal<>();

    public static void putDeployContext(DeployContext context) {
        DEPLOY_CONTEXT.set(context);
    }

    public static DeployContext getDeployContext() {
        return DEPLOY_CONTEXT.get();
    }

    public static void removeDeployContext() {
        DEPLOY_CONTEXT.remove();
    }
}
