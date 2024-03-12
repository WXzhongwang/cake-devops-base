package com.rany.cake.devops.base.domain.entity;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseEntity;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
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

    public void init() {
        this.gmtCreate = DateUtil.date();
        this.gmtModified = DateUtil.date();
        this.isDeleted = DeleteStatusEnum.NO.getValue();
    }
}
