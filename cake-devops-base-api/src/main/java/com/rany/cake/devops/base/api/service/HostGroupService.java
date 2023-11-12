package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.group.CreateGroupCommand;
import com.rany.cake.devops.base.api.command.group.DeleteGroupCommand;
import com.rany.cake.devops.base.api.command.group.ModifyGroupCommand;
import com.rany.cake.devops.base.api.dto.HostGroupDTO;
import com.rany.cake.devops.base.api.query.HostGroupBasicQuery;

/**
 * 主机服务
 *
 * @author zhongshengwang
 * @description 主机服务
 * @date 2023/1/15 17:33
 * @email 18668485565163.com
 */
public interface HostGroupService {

    /**
     * 创建主机组
     *
     * @param createHostCommand
     * @return
     */
    PojoResult<Long> createHostCommand(CreateGroupCommand createHostCommand);

    /**
     * 获取主机组信息
     *
     * @param hostGroupBasicQuery
     * @return
     */
    PojoResult<HostGroupDTO> getHostGroup(HostGroupBasicQuery hostGroupBasicQuery);


    /**
     * 删除主机组
     *
     * @param deleteGroupCommand
     * @return
     */
    PojoResult<Boolean> deleteHostGroup(DeleteGroupCommand deleteGroupCommand);

    /**
     * 更新主机组基本信息
     *
     * @param modifyGroupCommand
     * @return
     */
    PojoResult<Boolean> modifyHostGroup(ModifyGroupCommand modifyGroupCommand);
}
