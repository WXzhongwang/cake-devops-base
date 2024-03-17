package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HostMonitorDTO extends DTO {

    private String hostId;
    private Integer monitorStatus;
    private String monitorUrl;
    private String accessToken;
    private String agentVersion;

    private HostDTO host;
}
