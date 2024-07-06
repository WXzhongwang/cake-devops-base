package com.rany.cake.devops.base.api.query.message;

import com.rany.cake.devops.base.api.common.base.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WebSideMessagePageQuery extends BasePageQuery {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 已读状态
     */
    private Byte readStatus;
    /**
     * 用于轮询
     */
    private Long maxId;
}
