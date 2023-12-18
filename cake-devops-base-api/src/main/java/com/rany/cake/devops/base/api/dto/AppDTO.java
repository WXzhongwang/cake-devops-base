package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AppDTO extends DTO {
    private String  appId;
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
    private List<AppEnvDTO> appEnvList;
}
