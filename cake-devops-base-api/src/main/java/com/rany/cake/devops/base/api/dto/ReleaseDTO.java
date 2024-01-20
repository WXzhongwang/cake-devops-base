package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReleaseDTO extends DTO {
    private String releaseId;
    private String appId;
    private String releaseNo;
    private String approvalId;
    private Date releaseDate;
    private String releaseBranch;
    private String releaseCommitId;
    private String releaseVersion;
    private String envId;
    private String releaseStatus;
    private String rollback;
    private String rollbackId;

    private ApprovalDTO approvalDTO;
}
