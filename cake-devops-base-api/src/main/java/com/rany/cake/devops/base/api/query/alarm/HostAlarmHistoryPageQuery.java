package com.rany.cake.devops.base.api.query.alarm;

//import com.fasterxml.jackson.annotation.JsonFormat;

import com.rany.cake.devops.base.api.common.base.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class HostAlarmHistoryPageQuery extends BasePageQuery {

    private String hostId;
    private Integer alarmType;
    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
    private Double minValue;
    private Double maxValue;
}
