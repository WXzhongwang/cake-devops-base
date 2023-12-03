package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClusterDTO extends DTO {
    private Long id;

    private String clusterName;
    private String version;
    private String clusterType;
    private String status;
    private String connectionString;
    private String token;

    private String isDeleted;
    private Date gmtCreate;
    private Date gmtModified;
    private String creator;
    private String modifier;
}