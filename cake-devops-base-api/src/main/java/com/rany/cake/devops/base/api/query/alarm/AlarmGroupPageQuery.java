package com.rany.cake.devops.base.api.query.alarm;

import com.rany.cake.devops.base.api.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlarmGroupPageQuery extends BaseQuery {
    private String groupName;
}
