package com.rany.cake.devops.base.service.context;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 20:46
 * @email 18668485565163.com
 */
public interface Bootstrap {

    /**
     * 启动流程
     */
    void start();

    /**
     * 关闭
     */
    void shutdown();

    /**
     * 获取执行上下文
     *
     * @return
     */
    DeployContext getDeployContext();
}
