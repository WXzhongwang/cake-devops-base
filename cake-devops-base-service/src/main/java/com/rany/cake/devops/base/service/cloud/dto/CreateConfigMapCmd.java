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
public class CreateConfigMapCmd {
    private String namespace;
    private String appName;
    private String envName;
    private String envId;
    /**
     * 预计要更新的configMap键值对
     */
    private Map<String, String> configMap;

}
