package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 主机环境变量
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HostEnvDTO extends DTO {
    private Long id;
    private String hostId;
    private String attrKey;
    private String attrValue;
    private String description;
    private Date gmtCreate;
    private Date gmtModified;
}
