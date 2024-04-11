package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.system.CreateSystemEnvCommand;
import com.rany.cake.devops.base.api.command.system.DeleteSystemEnvCommand;
import com.rany.cake.devops.base.api.command.system.ModifySystemEnvCommand;
import com.rany.cake.devops.base.api.dto.SystemEnvDTO;
import com.rany.cake.devops.base.api.query.system.SystemEnvBasicQuery;
import com.rany.cake.devops.base.api.query.system.SystemEnvPageQuery;

import java.util.Map;


public interface SystemEnvService {

    /**
     * 创建
     *
     * @param command 创建
     * @return id
     */
    String createSystemEnv(CreateSystemEnvCommand command);

    /**
     * 保存环境变量
     *
     * @param envMap 变量map
     * @return VOID
     */
    void saveEnv(Map<String, String> envMap);

    /**
     * 编辑
     *
     * @param command 命令
     * @return 成功
     */
    Boolean modifySystemEnv(ModifySystemEnvCommand command);

    /**
     * 删除
     *
     * @param command 命令
     * @return 成功
     */
    Boolean deleteSystemEnv(DeleteSystemEnvCommand command);

    /**
     * 查看单个
     *
     * @param query 查询
     * @return 详情
     */
    SystemEnvDTO getSystemEnv(SystemEnvBasicQuery query);

    /**
     * 按名称获取系统变量名称
     *
     * @param envName 系统变量名称
     * @return 系统变量
     */
    SystemEnvDTO getSystemEnv(String envName);

    /**
     * 分页
     *
     * @param query 查询
     * @return 分页结果
     */
    Page<SystemEnvDTO> pageSystemEnv(SystemEnvPageQuery query);
}
