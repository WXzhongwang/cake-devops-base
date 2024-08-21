package com.rany.cake.devops.base.service.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScaleDeploymentCmd {
    private String namespace;
    /**
     * 发布deploymentName
     */
    private String deploymentName;
    private Integer replicas;
}
