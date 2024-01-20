package com.rany.cake.devops.base.service.context;

import com.rany.cake.devops.base.domain.enums.NodeStatus;
import com.rany.cake.devops.base.service.base.Constants;
import com.rany.cake.devops.base.service.plugins.annotation.PluginName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.AnnotationUtils;

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

    /**
     * 头节点
     */
    private final PluginNode head = new PluginNode("head");
    /**
     * 尾节点
     */
    private PluginNode tail;
    /**
     * 上下文
     */
    private final DeployContext deployContext;

    /**
     * 进度跟踪器
     */
    private final ProgressObserver observer;

    public DefaultDeployPipeline(DeployContext deployContext, ProgressObserver observer) {
        this.deployContext = deployContext;
        tail = head;
        this.observer = observer;
    }

    @Override
    public void start() {
        try {
            MDC.put(Constants.__TRACE_RELEASE_ID__, String.valueOf(deployContext.getRelease().getReleaseId().getReleaseId()));
            log.info("pipeline begin to start...");
            this.deployContext.start();
            // 设置开始执行时间
            this.observer.updateProgress(this.deployContext);
            head.getNext().init(this.deployContext);
            head.getNext().execute(this.deployContext);
            // 标注任务执行成功
            this.deployContext.success();
        } catch (Exception ex) {
            log.error("pipeline occur an error.", ex);
            // 标注任务执行失败
            this.deployContext.fail();
            throw ex;
        } finally {
            this.deployContext.end();
            this.observer.updateProgress(this.deployContext);
            MDC.clear();
        }
    }

    @Override
    public void shutdown() {
        log.info("pipeline系统结束运行");
    }

    @Override
    public void addFirst(Plugin... plugins) {
        PluginNode pre = head.getNext();
        for (Plugin plugin : plugins) {
            if (plugin == null) {
                continue;
            }
            PluginNode node = new PluginNode(plugin.getClass().getName());
            node.setPlugin(plugin);
            node.setObserver(observer);
            node.setNext(pre);
            pre = node;

            PluginName pluginNameAnnotation = AnnotationUtils.findAnnotation(plugin.getClass(), PluginName.class);
            String pluginName = plugin.getClass().getName();
            if (pluginNameAnnotation != null) {
                pluginName = pluginNameAnnotation.value();
            }
            DeployContext.Node executionNode = new DeployContext.Node(pluginName,
                    NodeStatus.AWAIT_EXECUTE.name());
            this.deployContext.addFirst(executionNode);
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

            PluginNode node = new PluginNode(plugin.getClass().getName());
            node.setPlugin(plugin);
            node.setObserver(observer);
            next.setNext(node);
            next = node;

            PluginName pluginNameAnnotation = AnnotationUtils.findAnnotation(plugin.getClass(), PluginName.class);
            String pluginName = plugin.getClass().getName();
            if (pluginNameAnnotation != null) {
                pluginName = pluginNameAnnotation.value();
            }
            DeployContext.Node executionNode = new DeployContext.Node(pluginName, NodeStatus.AWAIT_EXECUTE.name());
            this.deployContext.addLast(executionNode);
        }
        tail = next;
    }
}
