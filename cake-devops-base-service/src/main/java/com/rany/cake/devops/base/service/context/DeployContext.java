package com.rany.cake.devops.base.service.context;

import com.rany.cake.devops.base.api.enums.DeployStageEnum;
import com.rany.cake.devops.base.domain.aggegrate.App;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    private DeployStageEnum stage;
    private AppEnv appEnv;
    /**
     * 预计部署分支
     */
    private String branch;

    /**
     * 实际部署物镜像地址
     */
    private String deploymentImage;
    /**
     * agentImage
     */
    private String agentImage;

    /**
     * 当前插件名
     */
    private String currentPluginName;

    /**
     * 执行过的插件列表
     */
    private List<String> pluginNames = new ArrayList<>();
}
