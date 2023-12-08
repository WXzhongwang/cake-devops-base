package com.rany.cake.devops.base.service.context;

import com.rany.cake.devops.base.service.base.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

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

    public DefaultDeployPipeline(DeployContext deployContext) {
        this.deployContext = deployContext;
        tail = head;
    }

    @Override
    public void start() {
        try {
            MDC.put(Constants.__TRACE_RELEASE_ID__, String.valueOf(deployContext.getRelease().getId().getId()));
            log.info("pipeline begin to start...");
            head.getNext().execute(getDeployContext());
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
            next.setNext(node);
            next = node;
        }
        tail = next;
    }
}
