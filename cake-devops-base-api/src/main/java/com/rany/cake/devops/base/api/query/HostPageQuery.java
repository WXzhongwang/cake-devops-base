package com.rany.cake.devops.base.api.query;

import com.rany.cake.devops.base.api.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HostPageQuery extends BaseQuery {

    private String groupId;
}
