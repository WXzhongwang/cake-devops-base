package com.rany.cake.devops.base.api.query.template;

import com.rany.cake.devops.base.api.common.base.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ScriptTemplatePageQuery extends BasePageQuery {
    private String name;
}
