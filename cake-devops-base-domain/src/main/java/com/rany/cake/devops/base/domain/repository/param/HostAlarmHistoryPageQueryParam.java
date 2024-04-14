package com.rany.cake.devops.base.domain.repository.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class HostAlarmHistoryPageQueryParam extends BasePageParam {
    private String hostId;
    private Integer alarmType;
    private Date startDate;
    private Date endDate;
    private Double minValue;
    private Double maxValue;
}
