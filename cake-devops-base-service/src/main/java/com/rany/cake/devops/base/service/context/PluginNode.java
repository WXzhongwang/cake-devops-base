package com.rany.cake.devops.base.service.context;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

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
    /**
     * 进度跟踪器
     */
    private ProgressObserver observer;

    public void setNext(PluginNode next) {
        this.next = next;
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    public void setObserver(ProgressObserver observer) {
        this.observer = observer;
    }

    @Override
    public boolean execute(DeployContext context) {
        context.increment();
        DeployContext.Node node = context.getProgress().getSteps().get(context.current());
        node.setStartDate(new Date());
        context.setCurrentPluginName(this.plugin.getName());
        context.getPluginNames().add(this.plugin.getName());
        if (!plugin.execute(context) && stopWhenFailure()) {
            log.info("{}执行结束", this.plugin.getName());
            node.setEndDate(new Date());
            observer.updateProgress(context);
            return false;
        }
        log.info("{}执行结束", this.plugin.getName());
        node.setEndDate(new Date());
        observer.updateProgress(context);
        return next != null && next.execute(context);
    }

    @Override
    public boolean init(DeployContext context) {
        return plugin.init(context);
    }
}
