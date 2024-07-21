package com.rany.cake.devops.base.api.command.app;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import com.rany.cake.devops.base.api.dto.AppEnvDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 创建应用环境
 *
 * @author zhongshengwang
 * @description 创建应用环境
 * @date 2023/1/28 20:10
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateAppEnvCommand extends BaseCommand {
    private String appId;
    private AppEnvDTO env;
}
