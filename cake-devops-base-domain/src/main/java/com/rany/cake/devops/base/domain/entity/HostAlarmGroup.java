package com.rany.cake.devops.base.domain.entity;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseEntity;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 主机告警联系组（关系表）
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HostAlarmGroup extends BaseEntity<Long> {

    private String hostId;
    private String alarmGroupId;

    public HostAlarmGroup() {
        this.gmtCreate = DateUtil.date();
        this.gmtModified = DateUtil.date();
        this.isDeleted = DeleteStatusEnum.NO.getValue();
    }
}
