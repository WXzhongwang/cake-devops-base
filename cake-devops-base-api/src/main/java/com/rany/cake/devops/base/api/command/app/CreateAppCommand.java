package com.rany.cake.devops.base.api.command.app;

import com.rany.cake.devops.base.api.common.base.DTO;
import com.rany.cake.devops.base.api.dto.AppMemberDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 创建应用
 *
 * @author zhongshengwang
 * @description 创建应用
 * @date 2023/1/28 20:10
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateAppCommand extends DTO {
    private String appName;
    private String description;
    private String repo;
    private String defaultBranch;
    private String language;
    private String developMode;
    private Long owner;
    private String businessUnit;
    private String department;
    private List<AppMemberDTO> appMembers;
    // private List<AppEnvDTO> appEnvs;
    private String healthCheck;
}