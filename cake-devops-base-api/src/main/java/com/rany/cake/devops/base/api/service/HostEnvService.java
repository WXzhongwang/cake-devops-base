package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.host.env.*;
import com.rany.cake.devops.base.api.dto.HostEnvDTO;
import com.rany.cake.devops.base.api.query.host.env.HostEnvBasicQuery;
import com.rany.cake.devops.base.api.query.host.env.HostEnvPageQuery;
import com.rany.cake.devops.base.api.query.host.env.HostEnvViewQuery;

import java.util.LinkedHashMap;

/**
 * 环境变量服务
 *
 * @author zhongshengwang
 * @description 环境变量服务
 * @date 2023/1/15 21:18
 * @email 18668485565163.com
 */
public interface HostEnvService {

    /**
     * 创建
     *
     * @param command 创建
     * @return id
     */
    String createHostEnv(CreateHostEnvCommand command);

    /**
     * 编辑
     *
     * @param command 命令
     * @return 成功
     */
    Boolean modifyHostEnv(ModifyHostEnvCommand command);

    /**
     * 删除
     *
     * @param command 命令
     * @return 成功
     */
    Boolean deleteHostEnv(DeleteHostEnvCommand command);

    /**
     * 查看单个
     *
     * @param query 查询
     * @return 详情
     */
    HostEnvDTO getHostEnv(HostEnvBasicQuery query);

    /**
     * 分页
     *
     * @param query 查询
     * @return 分页结果
     */
    Page<HostEnvDTO> pageHostEnv(HostEnvPageQuery query);

    /**
     * 同步主机变量
     *
     * @param command 命令
     * @return 成功
     */
    Boolean asyncHostEnv(AsyncHostEnvCommand command);

    /**
     * 以视图方式查看
     *
     * @param query 查询
     * @return 结果
     */
    String view(HostEnvViewQuery query);

    /**
     * 获取系统环境变量
     *
     * @return map
     */
    LinkedHashMap<String, String> getMachineEnv(String hostId);

    /**
     * 保存视图
     *
     * @param command 保存视图
     * @return 查看视图
     */
    String saveView(HostEnvViewSaveCommand command);
}
