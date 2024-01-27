package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.account.CreateServerAccountCommand;
import com.rany.cake.devops.base.api.command.account.DeleteServerAccountCommand;
import com.rany.cake.devops.base.api.command.account.ModifyServerAccountCommand;
import com.rany.cake.devops.base.api.dto.ServerAccountDTO;
import com.rany.cake.devops.base.api.query.ServerAccountBasicQuery;
import com.rany.cake.devops.base.api.query.ServerAccountPageQuery;

/**
 * 主机账号服务
 *
 * @author zhongshengwang
 * @description 主机账号服务
 * @date 2023/1/15 17:33
 * @email 18668485565163.com
 */
public interface ServerAccountService {

    /**
     * 创建
     *
     * @param command 创建
     * @return 主机账号ID
     */
    PojoResult<String> createServerAccount(CreateServerAccountCommand command);

    /**
     * 更新
     *
     * @param command 更新
     * @return success
     */
    PojoResult<Boolean> modifyServerAccount(ModifyServerAccountCommand command);

    /**
     * 删除
     *
     * @param command 删除
     * @return success
     */
    PojoResult<Boolean> deleteServerAccount(DeleteServerAccountCommand command);

    /**
     * 获取详情
     *
     * @param basicQuery 详情
     * @return 详情
     */
    PojoResult<ServerAccountDTO> getServerAccount(ServerAccountBasicQuery basicQuery);


    /**
     * 分页查询列表
     *
     * @param serverAccountPageQuery 查询
     * @return 列表
     */
    PageResult<ServerAccountDTO> pageServerAccount(ServerAccountPageQuery serverAccountPageQuery);
}
