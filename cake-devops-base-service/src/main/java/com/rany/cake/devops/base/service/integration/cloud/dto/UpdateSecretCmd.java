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
public class UpdateSecretCmd {
    private String namespace;
    private String appName;
    private String envName;
    private String envId;
    /**
     * 更新的secretMap键值对
     */
    private Map<String, String> secretMap;

    /**
     * 原来的secretMap键值对
     */
    private Map<String, String> currentSecretMap;

}
