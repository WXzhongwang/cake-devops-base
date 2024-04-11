package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.template.CreateScriptTemplateCommand;
import com.rany.cake.devops.base.api.command.template.DeleteScriptTemplateCommand;
import com.rany.cake.devops.base.api.command.template.ModifyScriptTemplateCommand;
import com.rany.cake.devops.base.api.dto.ScriptTemplateDTO;
import com.rany.cake.devops.base.api.query.template.ScriptTemplateBasicQuery;
import com.rany.cake.devops.base.api.query.template.ScriptTemplatePageQuery;

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
    String createScriptTemplate(CreateScriptTemplateCommand command);

    /**
     * 更新
     *
     * @param command 更新
     * @return success
     */
    Boolean modifyScriptTemplate(ModifyScriptTemplateCommand command);

    /**
     * 删除
     *
     * @param command 删除
     * @return success
     */
    Boolean deleteScriptTemplate(DeleteScriptTemplateCommand command);

    /**
     * 获取详情
     *
     * @param basicQuery 详情
     * @return 详情
     */
    ScriptTemplateDTO getScriptTemplate(ScriptTemplateBasicQuery basicQuery);


    /**
     * 分页查询列表
     *
     * @param pageQuery 查询
     * @return 列表
     */
    Page<ScriptTemplateDTO> pageScriptTemplate(ScriptTemplatePageQuery pageQuery);
}
