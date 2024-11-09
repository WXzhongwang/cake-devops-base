package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeployHistoryDTO extends DTO {
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
    private String creator;
    private String creatorName;
}
