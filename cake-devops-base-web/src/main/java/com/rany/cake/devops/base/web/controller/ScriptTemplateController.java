package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.template.CreateScriptTemplateCommand;
import com.rany.cake.devops.base.api.command.template.DeleteScriptTemplateCommand;
import com.rany.cake.devops.base.api.command.template.ModifyScriptTemplateCommand;
import com.rany.cake.devops.base.api.dto.ScriptTemplateDTO;
import com.rany.cake.devops.base.api.query.ScriptTemplateBasicQuery;
import com.rany.cake.devops.base.api.query.ScriptTemplatePageQuery;
import com.rany.cake.devops.base.api.service.ScriptTemplateService;
import org.springframework.web.bind.annotation.*;

/**
 * webhook 配置
 */
@RestController
@RequestMapping("/api/devops/script-template")
public class ScriptTemplateController {

    private ScriptTemplateService webhookConfigService;


    @PostMapping("/create")
    public PojoResult<String> create(@RequestBody CreateScriptTemplateCommand command) {
        return webhookConfigService.createScriptTemplate(command);
    }

    @GetMapping("/get")
    public PojoResult<ScriptTemplateDTO> get(@RequestParam("id") Long templateId) {
        ScriptTemplateBasicQuery webhookBasicQuery = new ScriptTemplateBasicQuery();
        webhookBasicQuery.setTemplateId(templateId);
        return webhookConfigService.getScriptTemplate(webhookBasicQuery);
    }

    @PostMapping("/update")
    public PojoResult<Boolean> update(@RequestBody ModifyScriptTemplateCommand command) {
        return webhookConfigService.modifyScriptTemplate(command);
    }

    @PostMapping("/delete")
    public PojoResult<Boolean> delete(@RequestBody DeleteScriptTemplateCommand command) {
        return webhookConfigService.deleteScriptTemplate(command);
    }

    @PostMapping("/page")
    public PageResult<ScriptTemplateDTO> page(@RequestBody ScriptTemplatePageQuery webhookPageQuery) {
        return webhookConfigService.pageScriptTemplate(webhookPageQuery);
    }
}
