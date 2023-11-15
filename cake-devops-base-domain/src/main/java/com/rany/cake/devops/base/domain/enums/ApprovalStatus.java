package com.rany.cake.devops.base.domain.enums;

import lombok.Getter;

@Getter
public enum ApprovalStatus {
    PENDING,

    APPROVED,

    REPEALED,

    REJECTED;
}
