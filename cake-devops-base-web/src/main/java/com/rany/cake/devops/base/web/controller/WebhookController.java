package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.wehook.CreateWebhookCommand;
import com.rany.cake.devops.base.api.command.wehook.DeleteWebhookCommand;
import com.rany.cake.devops.base.api.command.wehook.ModifyWebHookCommand;
import com.rany.cake.devops.base.api.dto.WebHookConfigDTO;
import com.rany.cake.devops.base.api.query.webhook.WebhookBasicQuery;
import com.rany.cake.devops.base.api.query.webhook.WebhookPageQuery;
import com.rany.cake.devops.base.api.service.WebhookConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * webhook 配置
 */
@RestController
@RequestMapping("/api/devops/webhook")
public class WebhookController {
    @Resource
    private WebhookConfigService webhookConfigService;


    @PostMapping("/create")
    public PojoResult<String> create(@RequestBody CreateWebhookCommand command) {
        return PojoResult.succeed(webhookConfigService.createWebhook(command));
    }

    @GetMapping("/get")
    public PojoResult<WebHookConfigDTO> get(@RequestParam("id") Long webhookId) {
        WebhookBasicQuery webhookBasicQuery = new WebhookBasicQuery();
        webhookBasicQuery.setWebhookId(webhookId);
        return PojoResult.succeed(webhookConfigService.getWebhook(webhookBasicQuery));
    }

    @PostMapping("/update")
    public PojoResult<Boolean> update(@RequestBody ModifyWebHookCommand command) {
        return PojoResult.succeed(webhookConfigService.modifyWebhook(command));
    }

    @PostMapping("/delete")
    public PojoResult<Boolean> delete(@RequestBody DeleteWebhookCommand command) {
        return PojoResult.succeed(webhookConfigService.deleteWebhook(command));
    }

    @PostMapping("/page")
    public PageResult<WebHookConfigDTO> page(@RequestBody WebhookPageQuery webhookPageQuery) {
        return PageResult.succeed(webhookConfigService.pageWebhook(webhookPageQuery));
    }
}
