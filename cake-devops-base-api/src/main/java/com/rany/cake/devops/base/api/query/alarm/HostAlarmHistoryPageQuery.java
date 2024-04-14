package com.rany.cake.devops.base.api.query.alarm;

import com.rany.cake.devops.base.api.common.base.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class HostAlarmHistoryPageQuery extends BasePageQuery {

    private String hostId;
    private Integer alarmType;
    private Date startDate;
    private Date endDate;
    private Double minValue;
    private Double maxValue;
}
