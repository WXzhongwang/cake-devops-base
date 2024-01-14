package com.rany.cake.devops.base.domain.repository.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReleasePageQueryParam extends BasePageParam {
    /**
     * 应用ID
     */
    private String appId;
    /**
     * 环境ID
     */
    private String envId;
}
