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
    private String serverAddr;
    private Integer port;
    /**
     * 1. 基础账号密码认证
     * 2. 秘钥认证
     */
    private Integer authType;
    /**
     * 登录账号名
     */
    private String username;
    private String pwd;
    private String description;
    private String verified;
    private String status;

    /**
     * 打包机器权重
     */
    private Integer weight;
}
