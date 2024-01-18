package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.app.CreateAppCommand;
import com.rany.cake.devops.base.api.command.app.CreateAppEnvCommand;
import com.rany.cake.devops.base.api.dto.AppDTO;
import com.rany.cake.devops.base.api.dto.AppEnvDTO;
import com.rany.cake.devops.base.api.dto.DepartmentDTO;
import com.rany.cake.devops.base.api.query.AppBasicQuery;
import com.rany.cake.devops.base.api.query.AppEnvBasicQuery;
import com.rany.cake.devops.base.api.query.AppEnvQuery;
import com.rany.cake.devops.base.api.query.AppPageQuery;

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
    PojoResult<String> createApp(CreateAppCommand createAppCommand);

    /**
     * 获取应用详情
     *
     * @param appBasicQuery 应用详情
     * @return
     */
    PojoResult<AppDTO> getApp(AppBasicQuery appBasicQuery);


    /**
     * 分页查询应用列表
     *
     * @param appPageQuery 应用查询
     * @return app集合
     */
    PageResult<AppDTO> pageApp(AppPageQuery appPageQuery);


    /**
     * 创建应用环境
     *
     * @param createAppEnvCommand 创建应用环境
     * @return 环境ID
     */
    PojoResult<String> createAppEnv(CreateAppEnvCommand createAppEnvCommand);


    /**
     * 创建应用环境
     *
     * @param appEnvBasicQuery 查询单一应用环境
     * @return 环境ID
     */
    PojoResult<AppEnvDTO> getAppEnv(AppEnvBasicQuery appEnvBasicQuery);

    /**
     * 查询应用环境
     *
     * @param appEnvQuery 查询体
     * @return 环境列表
     */
    ListResult<AppEnvDTO> listAppEnv(AppEnvQuery appEnvQuery);

    /**
     * 查询所有团队
     *
     * @return 团队列表
     */
    ListResult<DepartmentDTO> listDepartments();

}
