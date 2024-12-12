package com.rany.cake.devops.base.domain.repository.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = true)
public class UserEventLogQueryPageParam extends BasePageParam {
    private Long userId;
    private String userName;
    private Integer eventType;
    private Integer eventClassify;
    private Date startDate;
    private Date endDate;
}