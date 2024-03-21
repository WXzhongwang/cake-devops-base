package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统环境变量
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemEnvDTO extends DTO {
    private Long id;
    private String attrKey;
    private String attrValue;
    private String description;
}
