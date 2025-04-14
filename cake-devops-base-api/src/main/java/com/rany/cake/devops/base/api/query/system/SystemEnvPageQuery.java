package com.rany.cake.devops.base.api.query.system;

import com.rany.cake.devops.base.api.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SystemEnvPageQuery extends BaseQuery {
    private String name;
    private Integer systemEnv;
}
