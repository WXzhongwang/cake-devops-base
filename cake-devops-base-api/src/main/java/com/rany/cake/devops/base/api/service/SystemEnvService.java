package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.system.CreateSystemEnvCommand;
import com.rany.cake.devops.base.api.command.system.DeleteSystemEnvCommand;
import com.rany.cake.devops.base.api.command.system.ModifySystemEnvCommand;
import com.rany.cake.devops.base.api.dto.SystemEnvDTO;
import com.rany.cake.devops.base.api.query.SystemEnvBasicQuery;
import com.rany.cake.devops.base.api.query.SystemEnvPageQuery;

import java.util.Map;

public interface SystemEnvService {

    /**
     * 创建
     *
     * @param command 创建
     * @return id
     */
    PojoResult<String> createSystemEnv(CreateSystemEnvCommand command);

    /**
     * 保存环境变量
     *
     * @param envMap 变量map
     * @return VOID
     */
    PojoResult<Void> saveEnv(Map<String, String> envMap);

    /**
     * 编辑
     *
     * @param command 命令
     * @return 成功
     */
    PojoResult<Boolean> modifySystemEnv(ModifySystemEnvCommand command);

    /**
     * 删除
     *
     * @param command 命令
     * @return 成功
     */
    PojoResult<Boolean> deleteSystemEnv(DeleteSystemEnvCommand command);

    /**
     * 查看单个
     *
     * @param query 查询
     * @return 详情
     */
    PojoResult<SystemEnvDTO> getSystemEnv(SystemEnvBasicQuery query);

    PojoResult<SystemEnvDTO> getSystemEnv(String envName);

    /**
     * 分页
     *
     * @param query 查询
     * @return 分页结果
     */
    PageResult<SystemEnvDTO> pageSystemEnv(SystemEnvPageQuery query);
}
