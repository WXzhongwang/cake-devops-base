package com.rany.cake.devops.base.api.query.host;

import com.rany.cake.devops.base.api.common.base.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class HostPageQuery extends BasePageQuery {

    private List<String> hostGroupsIds;

    private String name;

    private String hostName;
}
