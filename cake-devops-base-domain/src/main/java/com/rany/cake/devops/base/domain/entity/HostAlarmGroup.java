package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 主机告警联系组（关系表）
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class HostAlarmGroup extends BaseEntity<Long> {

    private String hostId;
    private String alarmGroupId;
}
