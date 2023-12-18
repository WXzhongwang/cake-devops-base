package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.host.CreateHostCommand;
import com.rany.cake.devops.base.api.command.host.DeleteHostCommand;
import com.rany.cake.devops.base.api.command.host.ModifyHostCommand;
import com.rany.cake.devops.base.api.dto.HostDTO;
import com.rany.cake.devops.base.api.query.HostBasicQuery;

/**
 * 主机服务
 *
 * @author zhongshengwang
 * @description 主机服务
 * @date 2023/1/15 17:33
 * @email 18668485565163.com
 */
public interface HostService {

    /**
     * 创建主机
     *
     * @param createHostCommand
     * @return
     */
    PojoResult<String> createHostCommand(CreateHostCommand createHostCommand);

    /**
     * 获取主机信息
     *
     * @param hostBasicQuery
     * @return
     */
    PojoResult<HostDTO> getHost(HostBasicQuery hostBasicQuery);


    /**
     * 删除主机
     *
     * @param deleteHostCommand
     * @return
     */
    PojoResult<Boolean> deleteHost(DeleteHostCommand deleteHostCommand);

    /**
     * 更新主机基本信息
     *
     * @param modifyHostCommand
     * @return
     */
    PojoResult<Boolean> modifyHost(ModifyHostCommand modifyHostCommand);
}
