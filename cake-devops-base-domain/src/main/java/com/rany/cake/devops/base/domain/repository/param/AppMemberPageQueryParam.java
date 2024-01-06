package com.rany.cake.devops.base.domain.repository.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppMemberPageQueryParam extends BasePageParam {
    private String appId;
    private List<String> accountIds;
}
