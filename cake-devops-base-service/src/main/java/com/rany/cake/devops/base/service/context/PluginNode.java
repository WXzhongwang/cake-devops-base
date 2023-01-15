package com.rany.cake.devops.base.service.context;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 20:56
 * @email 18668485565163.com
 */
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
    public void execute(DeployContext context) {
        plugin.execute(context);
    }
}
