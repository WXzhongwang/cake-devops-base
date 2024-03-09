package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 主机环境变量
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HostEnvDTO extends DTO {
    private String envId;
    private String attrKey;
    private String attrValue;
    private String description;
}
