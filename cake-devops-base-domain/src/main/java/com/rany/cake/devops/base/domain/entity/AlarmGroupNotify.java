package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 报警组通知方式
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AlarmGroupNotify extends BaseEntity<Long> {

    private Long groupId;
    private Long notifyId;
    private Integer notifyType;
}
