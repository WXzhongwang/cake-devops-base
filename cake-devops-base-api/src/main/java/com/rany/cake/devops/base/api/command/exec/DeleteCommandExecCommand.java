package com.rany.cake.devops.base.api.command.exec;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhongshengwang
 * @version 1.0
 * @date 2025/4/14 16:42
 * @slogon 找到银弹
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeleteCommandExecCommand extends BaseCommand {
    private Long id;
}
