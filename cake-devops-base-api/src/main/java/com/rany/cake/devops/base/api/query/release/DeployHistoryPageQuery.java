package com.rany.cake.devops.base.api.query.release;

import com.rany.cake.devops.base.api.common.base.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeployHistoryPageQuery extends BasePageQuery {
    private String envId;
    private String appId;
}
