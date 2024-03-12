package com.rany.cake.devops.base.domain.repository.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WebSideMessagePageQueryParam extends BasePageParam {

    private Long userId;
    private Byte readStatus;
    /**
     * 用于轮询
     */
    private Long maxId;
}
