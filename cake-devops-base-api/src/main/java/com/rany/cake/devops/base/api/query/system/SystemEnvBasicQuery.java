package com.rany.cake.devops.base.api.query.system;

import com.rany.cake.devops.base.api.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SystemEnvBasicQuery extends BaseQuery {

    private Long id;
}
