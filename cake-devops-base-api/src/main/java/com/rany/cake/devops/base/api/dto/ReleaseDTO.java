package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReleaseDTO extends DTO {
    private Long id;
    private Long appId;
    private String releaseNo;
    private Long approvalId;
    private Date releaseDate;
    private String releaseBranch;
    private String releaseCommitId;
    private String releaseVersion;
    private Long envId;
    private String releaseStatus;
    private String rollback;
    private Long rollbackId;
}
