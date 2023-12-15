package com.rany.cake.devops.base.domain.repository.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppQueryParam extends BasePageParam {
    private String appName;
    private String department;
    private String language;
}
