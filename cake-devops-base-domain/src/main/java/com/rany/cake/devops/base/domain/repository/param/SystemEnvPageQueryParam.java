package com.rany.cake.devops.base.domain.repository.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SystemEnvPageQueryParam extends BasePageParam {
    private String name;
    private Integer systemEnv;
}
