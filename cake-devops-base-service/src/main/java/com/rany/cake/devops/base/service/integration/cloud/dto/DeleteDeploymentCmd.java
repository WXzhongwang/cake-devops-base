package com.rany.cake.devops.base.service.integration.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteDeploymentCmd {
    private String namespace;
    private String deploymentName;
}
