package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.exec.CreateCommandExecCommand;
import com.rany.cake.devops.base.api.command.exec.DeleteCommandExecCommand;
import com.rany.cake.devops.base.api.dto.CommandExecDTO;
import com.rany.cake.devops.base.api.query.exec.CommandExecBasicQuery;
import com.rany.cake.devops.base.api.query.exec.CommandExecPageQuery;

import java.util.List;

/**
 * CommandExecCommand脚本
 *
 * @author zhongshengwang
 * @description 模版脚本
 * @date 2023/1/15 17:33
 * @email 18668485565163.com
 */
public interface CommandExecService {

    /**
     * 创建
     *
     * @param command 创建
     * @return webhookId
     */
    List<Long> createCommandExec(CreateCommandExecCommand command);

    /**
     * 删除
     *
     * @param command 删除
     * @return success
     */
    Boolean deleteCommandExec(DeleteCommandExecCommand command);

    /**
     * 获取详情
     *
     * @param basicQuery 详情
     * @return 详情
     */
    CommandExecDTO getCommandExec(CommandExecBasicQuery basicQuery);


    /**
     * 分页查询列表
     *
     * @param pageQuery 查询
     * @return 列表
     */
    Page<CommandExecDTO> pageCommandExec(CommandExecPageQuery pageQuery);
}
