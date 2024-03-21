package com.rany.cake.devops.base.api.command.system;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 删除主机
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeleteSystemEnvCommand extends BaseCommand {
    /**
     * 环境变量ID
     */
    private Long id;
}
