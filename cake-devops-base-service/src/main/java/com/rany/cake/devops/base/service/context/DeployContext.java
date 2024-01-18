package com.rany.cake.devops.base.service.context;

import com.alibaba.fastjson.JSON;
import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.aggregate.Cluster;
import com.rany.cake.devops.base.domain.aggregate.Namespace;
import com.rany.cake.devops.base.domain.aggregate.Release;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.enums.ReleaseStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * 上下文参数
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 20:52
 * @email 18668485565163.com
 */
@Data
public class DeployContext implements Serializable {

    private App app;

    /**
     * 基于发布单拉起发布pipeline
     */
    private Release release;
    /**
     * 发布集群
     */
    private Cluster cluster;
    /**
     * 发布namespace
     */
    private Namespace namespace;
    /**
     * 发布deploymentName
     */
    private String deploymentName;
    /**
     * 发布服务名
     */
    private String serviceName;
    /**
     * 服务端口
     */
    private Integer servicePort;
    /**
     * 容器端口
     */
    private Integer containerPort;
    /**
     * ingress名称
     */
    private String ingressName;

    /**
     * 发布环境
     */
    private AppEnv appEnv;

    /**
     * 实际部署物镜像地址
     */
    private String deploymentImage;
    /**
     * 当前插件名
     */
    private String currentPluginName;

    /**
     * 执行过的插件列表
     */
    private List<String> pluginNames = new ArrayList<>();


    private Map<String, Object> argMap = new HashMap<>();

    public void putArg(String key, Object value) {
        argMap.put(key, value);
    }

    /**
     * 进度
     */
    private Progress progress;

    public DeployContext(String pipeKey) {
        this.progress = new Progress();
        this.progress.setPipeKey(pipeKey);
        this.progress.setSteps(new ArrayList<>());
    }


    /**
     * 进度监控
     */
    @Data
    public static class Progress {
        /**
         * 流水线执行号
         */
        private String pipeKey;
        /**
         * 整体开始时间
         */
        private Date startDate;
        /**
         * 整体结束时间
         */
        private Date endDate;

        /**
         * 进度中会执行那些节点
         */
        private List<Node> steps;

        /**
         * 当前执行插件index
         */
        private Integer current;
    }

    /**
     * 执行插件往前走
     */
    public void increment() {
        this.progress.current++;
    }


    public Integer current() {
        return this.progress.current;
    }


    @Data
    public static class Node {
        /**
         * 整体开始时间
         */
        private Date startDate;
        /**
         * 整体结束时间
         */
        private Date endDate;

        /**
         * 节点名称
         */
        private String title;
        /**
         * 描述
         */
        private String description;

        public Node(String title, String description) {
            this.title = title;
            this.description = description;
        }
    }

    public String dump() {
        return JSON.toJSONString(this.progress);
    }

    public void fail() {
        this.release.setReleaseStatus(ReleaseStatus.FAILED.name());
        this.release.setGmtModified(new Date());
    }

    public void success() {
        this.release.setReleaseStatus(ReleaseStatus.FINISHED.name());
        this.release.setGmtModified(new Date());
    }

    public void start() {
        this.release.setReleaseStatus(ReleaseStatus.PENDING.name());
        this.release.setGmtModified(new Date());
    }
}
