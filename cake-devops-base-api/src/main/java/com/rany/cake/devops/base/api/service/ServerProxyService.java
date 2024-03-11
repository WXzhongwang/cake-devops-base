package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.proxy.CreateServerProxyCommand;
import com.rany.cake.devops.base.api.command.proxy.DeleteServerProxyCommand;
import com.rany.cake.devops.base.api.command.proxy.ModifyServerProxyCommand;
import com.rany.cake.devops.base.api.dto.ServerProxyDTO;
import com.rany.cake.devops.base.api.query.ServerProxyBasicQuery;
import com.rany.cake.devops.base.api.query.ServerProxyPageQuery;

/**
 * 主机代理服务
 *
 * @author zhongshengwang
 * @description 主机代理服务
 * @date 2023/1/15 17:33
 * @email 18668485565163.com
 */
public interface ServerProxyService {

    /**
     * 创建
     *
     * @param command 创建
     * @return 主机账号ID
     */
    PojoResult<String> createServerProxy(CreateServerProxyCommand command);

    /**
     * 更新
     *
     * @param command 更新
     * @return success
     */
    PojoResult<Boolean> modifyServerProxy(ModifyServerProxyCommand command);

    /**
     * 删除
     *
     * @param command 删除
     * @return success
     */
    PojoResult<Boolean> deleteServerProxy(DeleteServerProxyCommand command);

    /**
     * 获取详情
     *
     * @param basicQuery 详情
     * @return 详情
     */
    PojoResult<ServerProxyDTO> getServerProxy(ServerProxyBasicQuery basicQuery);


    /**
     * 分页查询列表
     *
     * @param pageQuery 查询
     * @return 列表
     */
    PageResult<ServerProxyDTO> pageServerProxy(ServerProxyPageQuery pageQuery);
}