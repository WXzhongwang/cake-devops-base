package com.rany.cake.devops.base.service.context;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 插件节点
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 20:56
 * @email 18668485565163.com
 */
public class PluginNode implements Plugin {
    private static final Logger log = LoggerFactory.getLogger("RabbitMq");

    /**
     * 下一个执行节点
     */
    @Getter
    private PluginNode next;

    private Plugin plugin;

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
        if (!plugin.execute(context) && stopWhenFailure()) {
            log.info("{}执行结束", this.plugin.getName());
            return false;
        }
        log.info("{}执行结束", this.plugin.getName());
        return next != null && next.execute(context);
    }

    @Override
    public boolean init(DeployContext context) {
        return plugin.init(context);
    }
}
