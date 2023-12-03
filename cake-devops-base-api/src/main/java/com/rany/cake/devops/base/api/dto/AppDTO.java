package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class AppDTO extends DTO {
    private Long id;
    private String appName;
    private String repo;
    private String defaultBranch;
    private String departmentAbbreviation;
    private String department;
    private String language;
    private String description;
    private String developMode;
    private String owner;
    private String healthCheck;
    private String status;
    private String isDeleted;
    private Date gmtCreate;
    private Date gmtModified;
    private String creator;
    private String modifier;
}
