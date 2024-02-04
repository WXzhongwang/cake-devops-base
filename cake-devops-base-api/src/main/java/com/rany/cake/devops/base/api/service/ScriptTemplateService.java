package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.template.CreateScriptTemplateCommand;
import com.rany.cake.devops.base.api.command.template.DeleteScriptTemplateCommand;
import com.rany.cake.devops.base.api.command.template.ModifyScriptTemplateCommand;
import com.rany.cake.devops.base.api.dto.ScriptTemplateDTO;
import com.rany.cake.devops.base.api.query.ScriptTemplateBasicQuery;
import com.rany.cake.devops.base.api.query.ScriptTemplatePageQuery;

/**
 * 模版脚本
 *
 * @author zhongshengwang
 * @description 模版脚本
 * @date 2023/1/15 17:33
 * @email 18668485565163.com
 */
public interface ScriptTemplateService {

    /**
     * 创建
     *
     * @param command 创建
     * @return webhookId
     */
    PojoResult<String> createScriptTemplate(CreateScriptTemplateCommand command);

    /**
     * 更新
     *
     * @param command 更新
     * @return success
     */
    PojoResult<Boolean> modifyScriptTemplate(ModifyScriptTemplateCommand command);

    /**
     * 删除
     *
     * @param command 删除
     * @return success
     */
    PojoResult<Boolean> deleteScriptTemplate(DeleteScriptTemplateCommand command);

    /**
     * 获取详情
     *
     * @param basicQuery 详情
     * @return 详情
     */
    PojoResult<ScriptTemplateDTO> getScriptTemplate(ScriptTemplateBasicQuery basicQuery);


    /**
     * 分页查询列表
     *
     * @param pageQuery 查询
     * @return 列表
     */
    PageResult<ScriptTemplateDTO> pageScriptTemplate(ScriptTemplatePageQuery pageQuery);
}
