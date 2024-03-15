package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.group.CreateGroupCommand;
import com.rany.cake.devops.base.api.command.group.DeleteGroupCommand;
import com.rany.cake.devops.base.api.command.group.ModifyGroupCommand;
import com.rany.cake.devops.base.api.dto.HostGroupDTO;
import com.rany.cake.devops.base.api.dto.HostGroupTreeDTO;
import com.rany.cake.devops.base.api.query.HostGroupBasicQuery;
import com.rany.cake.devops.base.api.query.HostGroupTreeQuery;

/**
 * 主机分组服务
 *
 * @author zhongshengwang
 * @description 主机分组服务
 * @date 2023/1/15 17:33
 * @email 18668485565163.com
 */
public interface HostGroupService {

    /**
     * 创建主机组
     *
     * @param createHostCommand command
     * @return 主机ID
     */
    PojoResult<String> createHostGroup(CreateGroupCommand createHostCommand);

    /**
     * 主机组树查询
     *
     * @param hostGroupTreeQuery query
     * @return 主机组树
     */
    ListResult<HostGroupTreeDTO> getHostGroupTree(HostGroupTreeQuery hostGroupTreeQuery);

    /**
     * 获取主机组信息
     *
     * @param hostGroupBasicQuery query
     * @return 主机组信息
     */
    PojoResult<HostGroupDTO> getHostGroup(HostGroupBasicQuery hostGroupBasicQuery);


    /**
     * 删除主机组
     *
     * @param deleteGroupCommand command
     * @return success
     */
    PojoResult<Boolean> deleteHostGroup(DeleteGroupCommand deleteGroupCommand);

    /**
     * 更新主机组基本信息
     *
     * @param modifyGroupCommand command
     * @return success
     */
    PojoResult<Boolean> modifyHostGroup(ModifyGroupCommand modifyGroupCommand);
}
