package com.rany.cake.devops.base.service.context;

import com.alibaba.fastjson.JSON;
import com.rany.cake.devops.base.domain.aggregate.*;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.util.enums.ReleaseStatus;
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

    /**
     * 打包机
     */
    private String hostId;
    /**
     * 打包机完整信息
     */
    private Host host;

    /**
     * 应用信息
     */
    private App app;

    /**
     * 基于发布单拉起发布pipeline
     */
    private Release release;
    /**
     * 检出分支
     */
    private String checkoutBranch;
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
     * 预计要更新的configMap键值对
     */
    private Map<String, String> configMap;

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
        this.progress.setCurrent(-1);
        this.progress.setSteps(new LinkedList<>());
    }

    public DeployContext() {
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
         * 状态，当某个插件执行失败，则返回失败
         * status:error
         */
        private String status;
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
        private LinkedList<Node> steps;

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

        /**
         * 计算耗时
         */
        private Long cost;

        public Node(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
            long endTimeStamp = endDate.getTime();
            long startTimestamp = startDate.getTime();
            // 计算相差的毫秒数
            long diff = endTimeStamp - startTimestamp;
            // 将毫秒数转换为秒数
            this.cost = diff / 1000;
        }
    }

    public String dump() {
        return JSON.toJSONString(this.progress);
    }

    public void fail() {
        this.release.setReleaseStatus(ReleaseStatus.FAILED.name());
        this.release.setGmtModified(new Date());
        this.progress.setStatus("error");
    }

    public void success() {
        this.release.setReleaseStatus(ReleaseStatus.FINISHED.name());
        this.release.setGmtModified(new Date());
        this.progress.setStatus("finish");
    }

    public void start() {
        this.release.setReleaseStatus(ReleaseStatus.PENDING.name());
        this.release.setGmtModified(new Date());
        this.progress.setStartDate(new Date());
        this.progress.setStatus("process");
    }

    public void end() {
        this.progress.setEndDate(new Date());
    }

    public void addLast(Node node) {
        this.progress.getSteps().addLast(node);
    }

    public void addFirst(Node node) {
        this.progress.getSteps().addFirst(node);
    }
}
