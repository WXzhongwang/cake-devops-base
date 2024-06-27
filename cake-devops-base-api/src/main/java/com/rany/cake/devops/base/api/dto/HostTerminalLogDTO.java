package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class HostTerminalLogDTO extends DTO {

    private String hostId;
    private String userId;
    private String username;
    private String accessToken;
    private Date connectedTime;
    private Date disconnectedTime;
    private Integer closeCode;
    private String screenPath;
}
