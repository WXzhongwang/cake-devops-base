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
public class UpdateConfigMapCmd {
    private String namespace;
    private String appName;
    private String envName;
    /**
     * 预计要更新的configMap键值对
     */
    private Map<String, String> configMap;

    /**
     * 预计要更新的configMap键值对
     */
    private Map<String, String> currentConfigMap;

}
