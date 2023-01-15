package com.rany.cake.devops.base.service.context;

/**
 * 部署管道上下文
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 17:29
 * @email 18668485565163.com
 */
public interface DeployPipeline extends Bootstrap {


    /**
     * 添加处理节点
     *
     * @param plugins
     */
    void addFirst(Plugin... plugins);

    /**
     * 添加处理节点
     *
     * @param plugins
     */
    void addLast(Plugin... plugins);
}
