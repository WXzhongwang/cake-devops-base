package com.rany.cake.devops.base.api.query.agent;

import com.rany.cake.devops.base.api.common.base.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HostMonitorPageQuery extends BasePageQuery {
    private String hostName;

    private String serverAddr;
}
