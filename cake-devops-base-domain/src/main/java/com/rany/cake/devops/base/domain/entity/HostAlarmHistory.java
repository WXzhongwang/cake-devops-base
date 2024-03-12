package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 告警历史
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class HostAlarmHistory extends BaseEntity<Long> {
    private String hostId;
    private Integer alarmType;
    private Double alarmValue;
    private Date alarmTime;
}
