package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class TerminalConnectConfig extends BaseEntity<Long> {

    private Integer rows;
    private Integer cols;
    private String terminalType;

    private String userId;
    private String username;
    private String hostId;
    private String hostName;
    private String serverAddr;
    private Date connectedTime;
    private Long logId;
}
