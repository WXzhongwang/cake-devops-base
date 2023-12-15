package com.rany.cake.devops.base.domain.repository.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BasePageParam extends BaseParam {

    private Integer pageNo = 0;

    private Integer pageSize = 10;

    private Boolean needTotal = true;
}
