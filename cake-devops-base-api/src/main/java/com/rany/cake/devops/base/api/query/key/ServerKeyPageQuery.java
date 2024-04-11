package com.rany.cake.devops.base.api.query.key;

import com.rany.cake.devops.base.api.common.base.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServerKeyPageQuery extends BasePageQuery {
    private String displayName;
    private Integer active;
    private String accountType;
}
