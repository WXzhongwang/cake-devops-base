package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeployHistory extends BaseEntity<Long> {

    private String envId;
    private String appId;
    private Date startTime;
    private Date endTime;
    private String deployStatus;
    private String imagePath;
    private String deploymentName;
    private String content;
    private String progress;
}
