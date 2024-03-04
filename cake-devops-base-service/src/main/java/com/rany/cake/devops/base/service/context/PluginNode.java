package com.rany.cake.devops.base.service.context;

import com.rany.cake.devops.base.util.enums.NodeStatus;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 插件节点
 *
 * @author zhongshengwang
 * @description 插件节点
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
     * 名称
     */
    @Getter
    private String name;
    /**
     * 进度跟踪器
     */
    private ProgressObserver observer;

    public void setName(String name) {
        this.name = name;
    }

    public PluginNode(String name) {
        this.name = name;
    }

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
        log.info("{}开始执行", this.name);
        context.increment();
        DeployContext.Node node = context.getProgress().getSteps().get(context.current() - 1);
        node.setStartDate(new Date());
        context.setCurrentPluginName(this.name);
        context.getPluginNames().add(this.name);
        if (!plugin.execute(context) && stopWhenFailure()) {
            log.info("{}执行结束", this.name);
            node.setEndDate(new Date());
            node.setDescription(NodeStatus.EXECUTED.name());
            observer.updateProgress(context);
            return false;
        }
        log.info("{}执行结束", this.name);
        node.setEndDate(new Date());
        node.setDescription(NodeStatus.EXECUTED.name());
        observer.updateProgress(context);
        return next != null && next.execute(context);
    }

    @Override
    public boolean init(DeployContext context) {
        if (!plugin.init(context)) {
            return false;
        }
        return next != null && next.init(context);
    }
}
