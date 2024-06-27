package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HostTerminalConfigDTO extends DTO {
    private Long id;

    private String hostId;

    private String terminalType;

    private String backgroundColor;

    private String fontColor;

    private Integer fontSize;

    private String fontFamily;
    /**
     * 是否开启url link 1开启 2关闭
     */
    private Integer enableWebLink;
}
