package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class HostTerminalLog extends BaseEntity<Long> {

    private String hostId;
    private String userId;
    private String username;
    private String accessToken;
    private Date connectedTime;
    private Date disconnectedTime;
    private Integer closeCode;
    private String screenPath;
}
