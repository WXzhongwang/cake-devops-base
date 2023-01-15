package com.rany.cake.devops.base.service.context;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 20:48
 * @email 18668485565163.com
 */
public interface Plugin {


    /**
     * 执行
     *
     * @param context
     */
    void execute(DeployContext context);
}
