package com.rany.cake.devops.base.api.query.app;

import com.rany.cake.devops.base.api.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppPageQuery extends BaseQuery {

    private String appName;
    private String department;
    private String language;
}
