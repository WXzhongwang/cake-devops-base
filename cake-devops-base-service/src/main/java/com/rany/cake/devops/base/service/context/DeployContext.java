package com.rany.cake.devops.base.service.context;

import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.aggregate.Cluster;
import com.rany.cake.devops.base.domain.aggregate.Namespace;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
