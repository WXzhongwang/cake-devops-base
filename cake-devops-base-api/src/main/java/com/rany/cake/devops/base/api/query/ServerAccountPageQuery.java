package com.rany.cake.devops.base.api.query;

import com.rany.cake.devops.base.api.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServerAccountPageQuery extends BaseQuery {
    private String hostId;
    private String displayName;
    private String username;
    private String accountType;
}
