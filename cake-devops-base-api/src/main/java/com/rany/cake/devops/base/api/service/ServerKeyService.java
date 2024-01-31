package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.account.CreateServerKeyCommand;
import com.rany.cake.devops.base.api.command.account.DeleteServerKeyCommand;
import com.rany.cake.devops.base.api.command.account.ModifyServerKeyCommand;
import com.rany.cake.devops.base.api.dto.ServerKeyDTO;
import com.rany.cake.devops.base.api.query.ServerAccountBasicQuery;
import com.rany.cake.devops.base.api.query.ServerKeyPageQuery;

/**
 * 主机秘钥服务
 *
 * @author zhongshengwang
 * @description 主机秘钥服务
 * @date 2023/1/15 17:33
 * @email 18668485565163.com
 */
public interface ServerKeyService {

    /**
     * 创建
     *
     * @param command 创建
     * @return 主机账号ID
     */
    PojoResult<String> createServerKey(CreateServerKeyCommand command);

    /**
     * 更新
     *
     * @param command 更新
     * @return success
     */
    PojoResult<Boolean> modifyServerKey(ModifyServerKeyCommand command);

    /**
     * 删除
     *
     * @param command 删除
     * @return success
     */
    PojoResult<Boolean> deleteServerKey(DeleteServerKeyCommand command);

    /**
     * 获取详情
     *
     * @param basicQuery 详情
     * @return 详情
     */
    PojoResult<ServerKeyDTO> getServerKey(ServerAccountBasicQuery basicQuery);


    /**
     * 分页查询列表
     *
     * @param serverKeyPageQuery 查询
     * @return 列表
     */
    PageResult<ServerKeyDTO> pageServerKey(ServerKeyPageQuery serverKeyPageQuery);
}
