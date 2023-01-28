package com.rany.cake.devops.base.api.command;

import com.rany.cake.devops.base.api.common.base.DTO;
import com.rany.cake.devops.base.api.dto.AppMemberDTO;
import com.rany.cake.devops.base.api.enums.CodeLanguageEnum;
import com.rany.cake.devops.base.api.enums.DevelopMode;
import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/28 20:10
 * @email 18668485565163.com
 */
@Data
public class CreateAppCommand extends DTO {
    private String appName;
    private String description;
    private String repo;
    private String defaultBranch;
    private CodeLanguageEnum language;
    private DevelopMode developMode;
    private Long owner;
    private String businessUnit;
    private String department;
    private List<AppMemberDTO> appMembers;
    private String healthCheck;
}
