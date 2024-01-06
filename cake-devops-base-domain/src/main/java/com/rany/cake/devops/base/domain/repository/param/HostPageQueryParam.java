package com.rany.cake.devops.base.domain.repository.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class HostPageQueryParam extends BasePageParam {
    private List<String> hostGroupsIds;

    private String name;

    private String hostName;
}
