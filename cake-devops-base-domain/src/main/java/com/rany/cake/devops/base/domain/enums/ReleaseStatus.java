package com.rany.cake.devops.base.domain.enums;

public enum ReleaseStatus {
    /**
     * 审批中
     */
    AWAIT_APPROVAL,
    /**
     * 待发布
     */
    READY,

    /**
     * 发布中
     */
    PENDING,

    /**
     * 已发布
     */
    FINISHED,
    /**
     * 发布失败
     */
    FAILED,

    /**
     * 已关闭
     */
    CLOSED,
}
