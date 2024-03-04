package com.rany.cake.devops.base.util.enums;

import lombok.Getter;

@Getter
public enum ApprovalStatus {
    PENDING,

    APPROVED,

    AUTO_APPROVED,

    REPEALED,

    REJECTED;
}
