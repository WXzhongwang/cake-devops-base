package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * 应用环境
 *
 * @author zhongshengwang
 * @description 应用环境
 * @date 2023/1/28 20:20
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppEnvDTO extends DTO {

    private String envId;
    private String appId;
    private String clusterId;
    private String env;
    private String envName;
    private List<String> domains;
    private ResourceStrategyDTO resourceStrategy;
    private Map<String, String> configMap;
    private String deploymentName;
    private String serviceName;
    private String ingressName;
    /**
     * 脚本
     */
    private String customBuildScript;
    /**
     * 是否自动扩容
     */
    private Boolean autoScaling;
    /**
     * 是否开启部署 需要审批
     */
    private Boolean needApproval;
    /**
     * 状态
     */
    private String status;

    /**
     * 发布状态
     */
    private String deployStatus;
    /**
     * 进度信息
     */
    private String progress;
}
