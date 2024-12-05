package com.rany.cake.devops.base.service.integration.cloud.dto;

import com.rany.cake.devops.base.domain.valueobject.ResourceStrategy;
import com.rany.cake.devops.base.domain.valueobject.VolumeMount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateDeploymentCmd {
    private String namespace;
    private String appName;
    private String deploymentName;
    private String deploymentImage;
    /**
     * 健康检查
     * 健康检查路径，端口后的uri，如：/health
     * 若未配置，将使用tcp检查
     */
    private String healthCheck;
    private Map<String, String> envVars;
    /**
     * 环境资源策略
     */
    private ResourceStrategy resourceStrategy;
    /**
     * 挂载
     */
    private List<VolumeMount> volumeMounts;
    /**
     * 容器端口
     */
    private Integer containerPort;
}
