package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ServerKeyDTO extends DTO {

    private Long id;

    /**
     * 展示名称
     */
    private String displayName;

    /**
     * 0普通账户/1管理员
     */
    private Integer accountType;

    /**
     * 协议
     */
    private String protocol;

    /**
     * 是否活跃可用
     */
    private String active;

    /**
     * 密钥文件存放地址
     */
    private String keyPath;
}
