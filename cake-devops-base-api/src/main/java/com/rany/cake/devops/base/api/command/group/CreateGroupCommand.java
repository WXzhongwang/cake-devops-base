package com.rany.cake.devops.base.api.command.group;


import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 添加主机组
 *
 * @author zhongshengwang
 * @description 添加主机组
 * @date 2022/12/27 20:40
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateGroupCommand extends BaseCommand {

    /**
     * name
     */
    private String name;
    private Integer sort;
    private String pid;
}
