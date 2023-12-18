package com.rany.cake.devops.base.api.command.group;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新主机组
 *
 * @author zhongshengwang
 * @description 更新主机组
 * @date 2022/12/30 22:02
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ModifyGroupCommand extends BaseCommand {

    private String groupId;
    private String name;
    private Integer sort;
    private String pid;
}
