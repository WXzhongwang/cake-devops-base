package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.repository.param.ScriptTemplateQueryParam;
import com.rany.cake.devops.base.infra.po.ScriptTemplatePO;

import java.util.List;

public interface ScriptTemplateDao {

    /**
     * 查询模版
     *
     * @param scriptTemplateQueryParam 模版查询
     * @return 模版查询
     */
    List<ScriptTemplatePO> queryScriptTemplate(ScriptTemplateQueryParam scriptTemplateQueryParam);
}