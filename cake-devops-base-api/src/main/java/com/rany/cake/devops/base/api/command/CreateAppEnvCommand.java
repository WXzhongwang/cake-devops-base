package com.rany.cake.devops.base.api.command;

import com.rany.cake.devops.base.api.common.base.DTO;
import com.rany.cake.devops.base.api.dto.AppEnvDTO;
import lombok.Data;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/28 20:10
 * @email 18668485565163.com
 */
@Data
public class CreateAppEnvCommand extends DTO {
    private Long appId;
    private AppEnvDTO env;
}
