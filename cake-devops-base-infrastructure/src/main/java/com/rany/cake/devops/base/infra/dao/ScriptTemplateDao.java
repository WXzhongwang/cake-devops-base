package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.entity.ScriptTemplate;
import com.rany.cake.devops.base.domain.repository.param.ScriptTemplateQueryParam;
import com.rany.cake.devops.base.infra.po.ScriptTemplatePO;

import java.util.List;

public interface ScriptTemplateDao {


    /**
     * 新增
     *
     * @param scriptTemplate 新增
     * @return 行数
     */
    int save(ScriptTemplate scriptTemplate);


    /**
     * 更新
     *
     * @param scriptTemplate 更新
     * @return 行数
     */
    int update(ScriptTemplate scriptTemplate);

    /**
     * 查询模版
     *
     * @param scriptTemplateQueryParam 模版查询
     * @return 模版查询
     */
    List<ScriptTemplatePO> queryScriptTemplate(ScriptTemplateQueryParam scriptTemplateQueryParam);
}