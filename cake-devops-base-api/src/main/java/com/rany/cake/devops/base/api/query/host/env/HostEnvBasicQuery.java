package com.rany.cake.devops.base.api.query.host.env;

import com.rany.cake.devops.base.api.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HostEnvBasicQuery extends BaseQuery {

    private Long envId;
}
