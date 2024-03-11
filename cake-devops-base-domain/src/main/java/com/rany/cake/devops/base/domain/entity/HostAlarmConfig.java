package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 主机告警配置
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class HostAlarmConfig extends BaseEntity<Long> {

    private String hostId;
    private Integer alarmType;
    private Double alarmThreshold;
    private Integer triggerThreshold;
    private Integer notifySilence;
}
