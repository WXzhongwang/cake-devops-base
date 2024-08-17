package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.app.*;
import com.rany.cake.devops.base.api.dto.AppDTO;
import com.rany.cake.devops.base.api.dto.AppEnvDTO;
import com.rany.cake.devops.base.api.dto.DepartmentDTO;
import com.rany.cake.devops.base.api.dto.PodDTO;
import com.rany.cake.devops.base.api.query.app.*;

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
    Boolean modifyAppConfigMap(ModifyConfigMapCommand modifyAppEnvConfigMapCommand);

    /**
     * 查询应用环境内POD信息
     *
     * @param appEnvPodQuery 查询pod
     * @return pod列表
     */
    List<PodDTO> listAppEnvPod(AppEnvPodQuery appEnvPodQuery);

    /**
     * 修改环境变量
     *
     * @param modifyEnvVarsCommand 修改环境变量
     * @return 是否成功
     */
    Boolean modifyAppEnvVars(ModifyEnvVarsCommand modifyEnvVarsCommand);

    /**
     * 修改环境资源配置
     *
     * @param modifyEnvResourceCommand 修改环境资源配置
     * @return 是否成功
     */
    Boolean modifyAppEnvResource(ModifyEnvResourceCommand modifyEnvResourceCommand);

    /**
     * 修改环境域名
     *
     * @param modifyAppEnvDomainCommand 修改环境域名
     * @return 是否成功
     */
    Boolean modifyAppEnvDomains(ModifyAppEnvDomainCommand modifyAppEnvDomainCommand);


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
