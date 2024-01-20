package com.rany.cake.devops.base.service.context;

/**
 * 插件接口
 *
 * @author zhongshengwang
 * @description 插件接口
 * @date 2023/1/15 20:48
 * @email 18668485565163.com
 */
public interface Plugin {


    /**
     * init 初始化配置相关
     *
     * @param context 上下文
     * @return 是否继续执行
     */
    boolean init(DeployContext context);


    /**
     * 执行
     *
     * @param context 上下文
     * @return 是否继续执行
     */
    boolean execute(DeployContext context);


    /**
     * plugin 执行失败是否可继续执行
     * 默认：仍然可以执行
     *
     * @return plugin 执行失败是否可继续执行
     */
    default boolean stopWhenFailure() {
        return false;
    }
}
