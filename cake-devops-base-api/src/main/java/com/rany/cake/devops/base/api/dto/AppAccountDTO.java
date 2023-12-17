package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class AppAccountDTO extends DTO {

    private Long id;
    private String accountName;
    private String phone;
    private String email;
    private Long tenantId;
    private Boolean isAdmin;
    private String accountType;
    private String status;
    private String isDeleted;
    private String lastLoginIp;
    private Date lastLoginTime;
    private String feature;
    private Date gmtCreate;
    private Date gmtModified;
    private String headImage;
    private String dingding;
    private String qq;
    private String wechat;
    private Date birthday;
    private String tags;

}
