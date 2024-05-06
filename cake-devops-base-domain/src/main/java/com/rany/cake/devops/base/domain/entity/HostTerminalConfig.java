package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class HostTerminalConfig extends BaseEntity<Long> {

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
