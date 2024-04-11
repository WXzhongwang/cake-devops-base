package com.rany.cake.devops.base.api.query.alarm;

import com.rany.cake.devops.base.api.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class HostAlarmHistoryPageQuery extends BaseQuery {

    private String hostId;
    private String alarmType;
    private Date startDate;
    private Date endDate;
}
