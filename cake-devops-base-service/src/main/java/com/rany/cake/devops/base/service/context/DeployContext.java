package com.rany.cake.devops.base.service.context;

import com.rany.cake.devops.base.api.enums.DeployStageEnum;
import com.rany.cake.devops.base.domain.aggegrate.App;
import lombok.Data;

import java.io.Serializable;

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

    /**
     * 实际部署物镜像地址
     */
    private String deploymentImage;
    /**
     * agentImage
     */
    private String agentImage;
}
