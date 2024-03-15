package com.rany.cake.devops.base.domain.repository.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HostMonitorQueryParam extends BasePageParam {
    private String hostName;
    private String hostAddr;
}
