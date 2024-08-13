package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.app.CreateAppCommand;
import com.rany.cake.devops.base.api.command.app.CreateAppEnvCommand;
import com.rany.cake.devops.base.api.command.app.ModifyConfigMapCommand;
import com.rany.cake.devops.base.api.command.app.ModifyEnvVarsCommand;
import com.rany.cake.devops.base.api.dto.AppDTO;
import com.rany.cake.devops.base.api.dto.AppEnvDTO;
import com.rany.cake.devops.base.api.dto.DepartmentDTO;
import com.rany.cake.devops.base.api.query.app.AppBasicQuery;
import com.rany.cake.devops.base.api.query.app.AppEnvBasicQuery;
import com.rany.cake.devops.base.api.query.app.AppEnvQuery;
import com.rany.cake.devops.base.api.query.app.AppPageQuery;

import java.util.List;

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
    String createApp(CreateAppCommand createAppCommand);

    /**
     * 获取应用详情
     *
     * @param appBasicQuery 应用详情
     * @return
     */
    AppDTO getApp(AppBasicQuery appBasicQuery);


    /**
     * 分页查询应用列表
     *
     * @param appPageQuery 应用查询
     * @return app集合
     */
    Page<AppDTO> pageApp(AppPageQuery appPageQuery);


    /**
     * 创建应用环境
     *
     * @param createAppEnvCommand 创建应用环境
     * @return 环境ID
     */
    String createAppEnv(CreateAppEnvCommand createAppEnvCommand);

    /**
     * 修改configMap
     *
     * @param modifyAppEnvConfigMapCommand 修改configMap
     * @return 是否成功
     */
    Boolean modifyAppEnvVars(ModifyConfigMapCommand modifyAppEnvConfigMapCommand);

    /**
     * 修改环境变量
     *
     * @param modifyEnvVarsCommand 修改环境变量
     * @return 是否成功
     */
    Boolean modifyAppEnvVars(ModifyEnvVarsCommand modifyEnvVarsCommand);


    /**
     * 创建应用环境
     *
     * @param appEnvBasicQuery 查询单一应用环境
     * @return 环境ID
     */
    AppEnvDTO getAppEnv(AppEnvBasicQuery appEnvBasicQuery);

    /**
     * 查询应用环境
     *
     * @param appEnvQuery 查询体
     * @return 环境列表
     */
    List<AppEnvDTO> listAppEnv(AppEnvQuery appEnvQuery);

    /**
     * 查询所有团队
     *
     * @return 团队列表
     */
    List<DepartmentDTO> listDepartments();

}
