package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.template.CreateScriptTemplateCommand;
import com.rany.cake.devops.base.api.command.template.DeleteScriptTemplateCommand;
import com.rany.cake.devops.base.api.command.template.ModifyScriptTemplateCommand;
import com.rany.cake.devops.base.api.dto.ScriptTemplateDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.api.query.ScriptTemplateBasicQuery;
import com.rany.cake.devops.base.api.query.ScriptTemplatePageQuery;
import com.rany.cake.devops.base.api.service.ScriptTemplateService;
import com.rany.cake.devops.base.domain.entity.ScriptTemplate;
import com.rany.cake.devops.base.domain.repository.ScriptTemplateRepository;
import com.rany.cake.devops.base.domain.repository.param.ScriptTemplateQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.ScriptTemplateDataAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@ShenyuService("/script-template/**")
@Slf4j
@AllArgsConstructor
public class ScriptTemplateRemoteService implements ScriptTemplateService {

    private ScriptTemplateRepository scriptTemplateRepository;
    private ScriptTemplateDataAdapter scriptTemplateDataAdapter;

    @Override
    public PojoResult<String> createScriptTemplate(CreateScriptTemplateCommand command) {
        ScriptTemplate scriptTemplate = new ScriptTemplate();
        scriptTemplate.setTemplateName(command.getTemplateName());
        scriptTemplate.setTemplateValue(command.getTemplateValue());
        scriptTemplate.setDescription(command.getDescription());
        scriptTemplateRepository.save(scriptTemplate);
        return PojoResult.succeed(scriptTemplate.getId().toString());
    }

    @Override
    public PojoResult<Boolean> modifyScriptTemplate(ModifyScriptTemplateCommand command) {
        ScriptTemplate scriptTemplate = scriptTemplateRepository.find(command.getTemplateId());
        if (scriptTemplate == null) {
            throw new DevOpsException(DevOpsErrorMessage.SCRIPT_TEMPLATE_NOT_FOUND);
        }
        scriptTemplate.setTemplateName(command.getTemplateName());
        scriptTemplate.setTemplateValue(command.getTemplateValue());
        scriptTemplate.setDescription(command.getDescription());
        scriptTemplateRepository.update(scriptTemplate);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> deleteScriptTemplate(DeleteScriptTemplateCommand command) {
        ScriptTemplate scriptTemplate = scriptTemplateRepository.find(command.getTemplateId());
        if (scriptTemplate == null) {
            throw new DevOpsException(DevOpsErrorMessage.SCRIPT_TEMPLATE_NOT_FOUND);
        }
        scriptTemplateRepository.remove(scriptTemplate);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<ScriptTemplateDTO> getScriptTemplate(ScriptTemplateBasicQuery basicQuery) {
        ScriptTemplate scriptTemplate = scriptTemplateRepository.find(basicQuery.getTemplateId());
        if (scriptTemplate == null) {
            throw new DevOpsException(DevOpsErrorMessage.SCRIPT_TEMPLATE_NOT_FOUND);
        }
        return PojoResult.succeed(scriptTemplateDataAdapter.sourceToTarget(scriptTemplate));
    }

    @Override
    public PageResult<ScriptTemplateDTO> pageScriptTemplate(ScriptTemplatePageQuery pageQuery) {
        ScriptTemplateQueryParam scriptTemplateQueryParam = scriptTemplateDataAdapter.convertParam(pageQuery);
        Page<ScriptTemplate> page = scriptTemplateRepository.page(scriptTemplateQueryParam);
        Collection<ScriptTemplate> items = page.getItems();
        List<ScriptTemplateDTO> webHookConfigDTOS = scriptTemplateDataAdapter.sourceToTarget(new ArrayList<>(items));
        Page<ScriptTemplateDTO> build = PageUtils.build(page, webHookConfigDTOS);
        return PageResult.succeed(build);
    }
}
