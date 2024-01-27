package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ServerAccountDTO extends DTO {
    private String serverAccountId;
    private String hostId;

    private String authMode;

    private String username;

    private String displayName;

    /**
     * 0普通账户/1管理员
     */
    private Integer accountType;

    private String protocol;

    /**
     * 是否活跃可用
     */
    private Boolean active;
}
