package com.rany.cake.devops.base.service.plugins.machine;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 打包机
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Machine extends DTO {
    private String hostId;
    private String name;
    private String hostName;
    private Integer port;
    private String username;
    private String pkey;
    private String desc;
    private String verified;

    /**
     * 打包机器权重
     */
    private Integer weight;
}
