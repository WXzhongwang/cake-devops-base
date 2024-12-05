package com.rany.cake.devops.base.api.query.log;

import com.rany.cake.devops.base.api.common.base.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserEventLogPageQuery extends BasePageQuery {
    private Long userId;
    private Integer eventType;
    private Integer eventClassify;
    private Date startDate;
    private Date endDate;
}
