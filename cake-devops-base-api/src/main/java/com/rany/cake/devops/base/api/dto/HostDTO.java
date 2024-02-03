package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HostDTO extends DTO {
    private String hostId;
    private String name;
    private String hostName;
    private String serverAddr;
    private Integer port;
    private Integer authType;
    private String username;
    private String pwd;
    private String desc;
    private String verified;
    private String status;
}
