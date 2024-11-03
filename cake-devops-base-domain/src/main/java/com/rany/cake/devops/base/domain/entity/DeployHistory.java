package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import com.rany.cake.toolkit.lang.time.Dates;
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
    private String releaseId;

    /**
     * 用于追溯日志
     */
    private String pipeKey;

    public DeployHistory() {
        this.isDeleted = DeleteStatusEnum.NO.getValue();
        this.gmtCreate = this.gmtModified = Dates.date();
    }
}
