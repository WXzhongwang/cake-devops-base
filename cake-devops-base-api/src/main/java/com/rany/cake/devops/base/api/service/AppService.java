package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.app.CreateAppCommand;
import com.rany.cake.devops.base.api.command.app.CreateAppEnvCommand;
import com.rany.cake.devops.base.api.dto.AppEnvDTO;
import com.rany.cake.devops.base.api.query.AppEnvQuery;

/**
 * 应用服务
 *
 * @author zhongshengwang
 * @description 应用服务
 * @date 2023/1/15 17:33
 * @email 18668485565163.com
 */
public interface AppService {

    /**
     * 创建应用
     *
     * @param createAppCommand 创建应用
     * @return 应用ID
     */
    PojoResult<Long> createApp(CreateAppCommand createAppCommand);


    /**
     * 创建应用环境
     *
     * @param createAppEnvCommand 创建应用环境
     * @return 环境ID
     */
    PojoResult<Long> createAppEnv(CreateAppEnvCommand createAppEnvCommand);

    /**
     * 查询应用环境
     *
     * @param appEnvQuery 查询体
     * @return 环境列表
     */
    ListResult<AppEnvDTO> listAppEnv(AppEnvQuery appEnvQuery);

}
