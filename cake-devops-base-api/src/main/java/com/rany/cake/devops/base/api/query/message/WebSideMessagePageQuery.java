package com.rany.cake.devops.base.api.query.message;

import com.rany.cake.devops.base.api.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WebSideMessagePageQuery extends BaseQuery {

    private Long userId;
    private Byte readStatus;
    /**
     * 用于轮询
     */
    private Long maxId;
}
