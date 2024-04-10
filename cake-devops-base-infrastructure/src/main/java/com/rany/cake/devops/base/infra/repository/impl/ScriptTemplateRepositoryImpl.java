package com.rany.cake.devops.base.infra.repository.impl;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.cake.devops.base.domain.entity.ScriptTemplate;
import com.rany.cake.devops.base.domain.repository.ScriptTemplateRepository;
import com.rany.cake.devops.base.domain.repository.param.ScriptTemplateQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.infra.aop.PagingQuery;
import com.rany.cake.devops.base.infra.convertor.ScriptTemplateDataConvertor;
import com.rany.cake.devops.base.infra.dao.ScriptTemplateDao;
import com.rany.cake.devops.base.infra.mapper.ScriptTemplatePOMapper;
import com.rany.cake.devops.base.infra.po.ScriptTemplatePO;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 脚本模版
 *
 * @author zhongshenwang
 */
@AllArgsConstructor
@Service
public class ScriptTemplateRepositoryImpl implements ScriptTemplateRepository {
    private ScriptTemplatePOMapper scriptTemplatePOMapper;
    private ScriptTemplateDao scriptTemplateDao;
    private ScriptTemplateDataConvertor scriptTemplateConvertor;

    @Override
    public ScriptTemplate find(Long id) {
        ScriptTemplatePO scriptTemplatePO = scriptTemplatePOMapper.selectByPrimaryKey(id);
        return scriptTemplateConvertor.targetToSource(scriptTemplatePO);
    }

    @Override
    public void remove(ScriptTemplate scriptTemplate) {
        ScriptTemplatePO scriptTemplatePO = scriptTemplatePOMapper.selectByPrimaryKey(scriptTemplate.getId());
        scriptTemplatePO.setIsDeleted(DeleteStatusEnum.YES.getValue());
        scriptTemplatePOMapper.updateByPrimaryKey(scriptTemplatePO);
    }

    @Override
    public void save(ScriptTemplate scriptTemplate) {
        ScriptTemplatePO scriptTemplatePO = scriptTemplateConvertor.sourceToTarget(scriptTemplate);
        scriptTemplatePOMapper.insertSelective(scriptTemplatePO);
    }

    @Override
    public void update(ScriptTemplate scriptTemplate) {
        ScriptTemplatePO scriptTemplatePO = scriptTemplateConvertor.sourceToTarget(scriptTemplate);
        scriptTemplatePOMapper.updateByPrimaryKey(scriptTemplatePO);
    }

    @Override
    @PagingQuery
    public Page<ScriptTemplate> page(ScriptTemplateQueryParam queryParam) {
        List<ScriptTemplatePO> scriptTemplatePOS = scriptTemplateDao.queryScriptTemplate(queryParam);
        PageInfo<ScriptTemplatePO> pageInfo = new PageInfo<>(scriptTemplatePOS);
        List<ScriptTemplate> configs = scriptTemplateConvertor.targetToSource(scriptTemplatePOS);
        return PageUtils.build(pageInfo, configs);
    }
}
