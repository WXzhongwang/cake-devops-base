package com.rany.cake.devops.base.api.command.system;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新系统环境变量
 *
 * @author zhongshengwang
 * @description 更新系统环境变量
 * @date 2022/12/30 22:02
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ModifySystemEnvCommand extends BaseCommand {
    /**
     * 环境变量ID
     */
    private Long id;

    private String attrKey;
    private String attrValue;
    private String description;
}
