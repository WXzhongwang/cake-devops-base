package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.template.CreateScriptTemplateCommand;
import com.rany.cake.devops.base.api.command.template.DeleteScriptTemplateCommand;
import com.rany.cake.devops.base.api.command.template.ModifyScriptTemplateCommand;
import com.rany.cake.devops.base.api.dto.ScriptTemplateDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.api.query.template.ScriptTemplateBasicQuery;
import com.rany.cake.devops.base.api.query.template.ScriptTemplatePageQuery;
import com.rany.cake.devops.base.api.service.ScriptTemplateService;
import com.rany.cake.devops.base.domain.entity.ScriptTemplate;
import com.rany.cake.devops.base.domain.repository.ScriptTemplateRepository;
import com.rany.cake.devops.base.domain.repository.param.ScriptTemplateQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.ScriptTemplateDataAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
//@ShenyuService("/script-template/**")
@Slf4j
@AllArgsConstructor
public class ScriptTemplateRemoteService implements ScriptTemplateService {

    private ScriptTemplateRepository scriptTemplateRepository;
    private ScriptTemplateDataAdapter scriptTemplateDataAdapter;

    @Override
    public String createScriptTemplate(CreateScriptTemplateCommand command) {
        ScriptTemplate scriptTemplate = new ScriptTemplate();
        scriptTemplate.setTemplateName(command.getTemplateName());
        scriptTemplate.setTemplateValue(command.getTemplateValue());
        scriptTemplate.setDescription(command.getDescription());
        scriptTemplate.init(command.getUser());
        scriptTemplateRepository.save(scriptTemplate);
        return scriptTemplate.getId().toString();
    }

    @Override
    public Boolean modifyScriptTemplate(ModifyScriptTemplateCommand command) {
        ScriptTemplate scriptTemplate = scriptTemplateRepository.find(command.getId());
        if (scriptTemplate == null) {
            throw new DevOpsException(DevOpsErrorMessage.SCRIPT_TEMPLATE_NOT_FOUND);
        }
        scriptTemplate.setTemplateName(command.getTemplateName());
        scriptTemplate.setTemplateValue(command.getTemplateValue());
        scriptTemplate.setDescription(command.getDescription());
        scriptTemplate.modify(command.getUser());
        scriptTemplateRepository.update(scriptTemplate);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteScriptTemplate(DeleteScriptTemplateCommand command) {
        ScriptTemplate scriptTemplate = scriptTemplateRepository.find(command.getTemplateId());
        if (scriptTemplate == null) {
            throw new DevOpsException(DevOpsErrorMessage.SCRIPT_TEMPLATE_NOT_FOUND);
        }
        scriptTemplate.delete(command.getUser());
        scriptTemplateRepository.remove(scriptTemplate);
        return Boolean.TRUE;
    }

    @Override
    public ScriptTemplateDTO getScriptTemplate(ScriptTemplateBasicQuery basicQuery) {
        ScriptTemplate scriptTemplate = scriptTemplateRepository.find(basicQuery.getTemplateId());
        if (scriptTemplate == null) {
            throw new DevOpsException(DevOpsErrorMessage.SCRIPT_TEMPLATE_NOT_FOUND);
        }
        return scriptTemplateDataAdapter.sourceToTarget(scriptTemplate);
    }

    @Override
    public Page<ScriptTemplateDTO> pageScriptTemplate(ScriptTemplatePageQuery pageQuery) {
        ScriptTemplateQueryParam scriptTemplateQueryParam = scriptTemplateDataAdapter.convertParam(pageQuery);
        Page<ScriptTemplate> page = scriptTemplateRepository.page(scriptTemplateQueryParam);
        Collection<ScriptTemplate> items = page.getItems();
        List<ScriptTemplateDTO> webHookConfigDTOS = scriptTemplateDataAdapter.sourceToTarget(new ArrayList<>(items));
        return PageUtils.build(page, webHookConfigDTOS);
    }
}
