package com.rany.cake.devops.base.service.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEnvVarsCmd {
    private String namespace;
    private String deploymentName;
    private Map<String, String> envVars;
}
