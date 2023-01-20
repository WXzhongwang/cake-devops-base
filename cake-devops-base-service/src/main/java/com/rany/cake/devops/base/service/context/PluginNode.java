package com.rany.cake.devops.base.service.context;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 20:56
 * @email 18668485565163.com
 */
@Slf4j
public class PluginNode implements Plugin {

    /**
     * 下一个执行节点
     */
    private PluginNode next;

    private Plugin plugin;

    public PluginNode getNext() {
        return next;
    }

    public void setNext(PluginNode next) {
        this.next = next;
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean execute(DeployContext context) {
        context.setCurrentPluginName(this.plugin.getName());
        context.getPluginNames().add(this.plugin.getName());
        if (!plugin.execute(context)) {
            log.info("{}执行结束", this.plugin.getName());
            return false;
        }
        log.info("{}执行结束", this.plugin.getName());
        return next != null && next.execute(context);
    }
}
