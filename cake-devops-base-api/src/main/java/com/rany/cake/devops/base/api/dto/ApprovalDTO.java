package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApprovalDTO extends DTO {

    private Long id;
    private String docAddress;
    private Date changeDate;
    private String approvalStatus;
    private String comment;
}
