package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.wehook.CreateWebhookCommand;
import com.rany.cake.devops.base.api.command.wehook.DeleteWebhookCommand;
import com.rany.cake.devops.base.api.command.wehook.ModifyWebHookCommand;
import com.rany.cake.devops.base.api.dto.WebHookConfigDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.api.query.webhook.WebhookBasicQuery;
import com.rany.cake.devops.base.api.query.webhook.WebhookPageQuery;
import com.rany.cake.devops.base.api.service.WebhookConfigService;
import com.rany.cake.devops.base.domain.entity.WebhookConfig;
import com.rany.cake.devops.base.domain.repository.WebhookConfigRepository;
import com.rany.cake.devops.base.domain.repository.param.WebhookConfigQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.WebhookDataAdapter;
import com.rany.cake.devops.base.util.enums.WebHookType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@ShenyuService("/webhook-config/**")
@Slf4j
@AllArgsConstructor
public class WebhookConfigRemoteService implements WebhookConfigService {

    private WebhookConfigRepository webhookConfigRepository;
    private WebhookDataAdapter webhookDataAdapter;

    @Override
    public String createWebhook(CreateWebhookCommand command) {
        WebhookConfig env = new WebhookConfig();
        env.setWebhookName(command.getWebhookName());
        env.setWebhookType(WebHookType.DINGDING.getType());
        env.setWebhookUrl(command.getWebhookUrl());
        env.setWebhookConfig(command.getWebhookConfig());
        env.init(command.getUser());
        webhookConfigRepository.save(env);
        return env.getId().toString();
    }

    @Override
    public Boolean modifyWebhook(ModifyWebHookCommand command) {
        WebhookConfig webhookConfig = webhookConfigRepository.find(command.getId());
        if (webhookConfig == null) {
            throw new DevOpsException(DevOpsErrorMessage.HOOK_NOT_FOUND);
        }
        webhookConfig.setWebhookName(command.getWebhookName());
        webhookConfig.setWebhookType(WebHookType.DINGDING.getType());
        webhookConfig.setWebhookUrl(command.getWebhookUrl());
        webhookConfig.setWebhookConfig(command.getWebhookConfig());
        webhookConfig.modify(command.getUser());
        webhookConfigRepository.update(webhookConfig);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteWebhook(DeleteWebhookCommand command) {
        WebhookConfig webhookConfig = webhookConfigRepository.find(command.getId());
        if (webhookConfig == null) {
            throw new DevOpsException(DevOpsErrorMessage.HOOK_NOT_FOUND);
        }
        webhookConfig.delete(command.getUser());
        webhookConfigRepository.remove(webhookConfig);
        return Boolean.TRUE;
    }

    @Override
    public WebHookConfigDTO getWebhook(WebhookBasicQuery basicQuery) {
        WebhookConfig webhookConfig = webhookConfigRepository.find(basicQuery.getWebhookId());
        if (webhookConfig == null) {
            throw new DevOpsException(DevOpsErrorMessage.HOOK_NOT_FOUND);
        }
        return webhookDataAdapter.sourceToTarget(webhookConfig);
    }

    @Override
    public Page<WebHookConfigDTO> pageWebhook(WebhookPageQuery pageQuery) {
        WebhookConfigQueryParam webhookConfigQueryParam = webhookDataAdapter.convertParam(pageQuery);
        Page<WebhookConfig> page = webhookConfigRepository.page(webhookConfigQueryParam);
        Collection<WebhookConfig> items = page.getItems();
        List<WebHookConfigDTO> webHookConfigDTOS = webhookDataAdapter.sourceToTarget(new ArrayList<>(items));
        return PageUtils.build(page, webHookConfigDTOS);
    }
}
