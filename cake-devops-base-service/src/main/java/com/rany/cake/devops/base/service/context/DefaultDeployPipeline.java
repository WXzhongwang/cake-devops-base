package com.rany.cake.devops.base.service.context;

import com.rany.cake.devops.base.service.base.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Date;
import java.util.List;

/**
 * pipe
 *
 * @author zhongshengwang
 * @description pipe
 * @date 2023/1/15 20:58
 * @email 18668485565163.com
 */
public class DefaultDeployPipeline implements DeployPipeline {

    private static final Logger log = LoggerFactory.getLogger("RabbitMq");

    private final PluginNode head = new PluginNode();
    private PluginNode tail;
    private final DeployContext deployContext;

    /**
     * 进度跟踪器
     */
    private final ProgressObserver observer;

    public DefaultDeployPipeline(DeployContext deployContext) {
        this.deployContext = deployContext;
        tail = head;
        this.observer = new ProgressUpdater();
    }

    @Override
    public void start() {
        try {
            MDC.put(Constants.__TRACE_RELEASE_ID__, String.valueOf(deployContext.getRelease().getReleaseId().getReleaseId()));
            log.info("pipeline begin to start...");
            // 设置开始执行时间
            this.deployContext.getProgress().setStartDate(new Date());
            initialProgress();
            head.getNext().execute(this.deployContext);
        } catch (Exception ex) {
            log.error("pipeline occur an error.", ex);
            throw ex;
        } finally {
            MDC.clear();
        }
    }

    @Override
    public void shutdown() {
        log.info("pipeline系统结束运行");
    }

    @Override
    public DeployContext getDeployContext() {
        return this.deployContext;
    }

    @Override
    public void addFirst(Plugin... plugins) {
        PluginNode pre = head.getNext();
        for (Plugin plugin : plugins) {
            if (plugin == null) {
                continue;
            }
            PluginNode node = new PluginNode();
            node.setPlugin(plugin);
            node.setObserver(observer);
            node.setNext(pre);
            pre = node;
        }

        head.setNext(pre);
    }

    @Override
    public void addLast(Plugin... plugins) {
        PluginNode next = tail;
        for (Plugin plugin : plugins) {
            if (plugin == null) {
                continue;
            }

            PluginNode node = new PluginNode();
            node.setPlugin(plugin);
            node.setObserver(observer);
            next.setNext(node);
            next = node;
        }
        tail = next;
    }
    
    /**
     * 初始化进度
     */
    public void initialProgress() {
        PluginNode current = this.head;
        this.deployContext.getProgress().setCurrent(0);
        while (current.getNext() != null) {
            List<DeployContext.Node> steps = this.deployContext.getProgress().getSteps();
            steps.add(new DeployContext.Node(current.getName(), "待执行"));
            current = current.getNext();
        }
    }
}
