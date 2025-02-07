package com.rany.cake.devops.base.service.integration.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSecretCmd {
    private String namespace;
    private String appName;
    private String envName;
    private String envId;
    /**
     * secretData
     */
    private Map<String, byte[]> secretData;

}
