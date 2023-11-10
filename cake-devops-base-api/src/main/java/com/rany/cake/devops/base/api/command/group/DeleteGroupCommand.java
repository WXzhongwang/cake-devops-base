package com.rany.cake.devops.base.api.command.group;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 删除主机组
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeleteGroupCommand extends BaseCommand {
    /**
     * groupId
     */
    private Long groupId;
}
