package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.wehook.CreateWebhookCommand;
import com.rany.cake.devops.base.api.command.wehook.DeleteWebhookCommand;
import com.rany.cake.devops.base.api.command.wehook.ModifyWebHookCommand;
import com.rany.cake.devops.base.api.dto.WebHookConfigDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.api.query.WebhookBasicQuery;
import com.rany.cake.devops.base.api.query.WebhookPageQuery;
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
    public PojoResult<String> createWebhook(CreateWebhookCommand command) {
        WebhookConfig env = new WebhookConfig();
        env.setWebhookName(command.getWebhookName());
        env.setWebhookType(WebHookType.DINGDING.name());
        env.setWebhookUrl(command.getWebhookUrl());
        env.setWebhookConfig(command.getWebhookConfig());
        webhookConfigRepository.save(env);
        return PojoResult.succeed(env.getId().toString());
    }

    @Override
    public PojoResult<Boolean> modifyWebhook(ModifyWebHookCommand command) {
        WebhookConfig webhookConfig = webhookConfigRepository.find(command.getWebhookId());
        if (webhookConfig == null) {
            throw new DevOpsException(DevOpsErrorMessage.HOOK_NOT_FOUND);
        }
        webhookConfig.setWebhookName(command.getWebhookName());
        webhookConfig.setWebhookType(WebHookType.DINGDING.name());
        webhookConfig.setWebhookUrl(command.getWebhookUrl());
        webhookConfig.setWebhookConfig(command.getWebhookConfig());
        webhookConfigRepository.update(webhookConfig);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> deleteWebhook(DeleteWebhookCommand command) {
        WebhookConfig webhookConfig = webhookConfigRepository.find(command.getWebhookId());
        if (webhookConfig == null) {
            throw new DevOpsException(DevOpsErrorMessage.HOOK_NOT_FOUND);
        }
        webhookConfigRepository.remove(webhookConfig);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<WebHookConfigDTO> getWebhook(WebhookBasicQuery basicQuery) {
        WebhookConfig webhookConfig = webhookConfigRepository.find(basicQuery.getWebhookId());
        if (webhookConfig == null) {
            throw new DevOpsException(DevOpsErrorMessage.HOOK_NOT_FOUND);
        }
        return PojoResult.succeed(webhookDataAdapter.sourceToTarget(webhookConfig));
    }

    @Override
    public PageResult<WebHookConfigDTO> pageWebhook(WebhookPageQuery pageQuery) {
        WebhookConfigQueryParam webhookConfigQueryParam = webhookDataAdapter.convertParam(pageQuery);
        Page<WebhookConfig> page = webhookConfigRepository.page(webhookConfigQueryParam);
        Collection<WebhookConfig> items = page.getItems();
        List<WebHookConfigDTO> webHookConfigDTOS = webhookDataAdapter.sourceToTarget(new ArrayList<>(items));
        Page<WebHookConfigDTO> build = PageUtils.build(page, webHookConfigDTOS);
        return PageResult.succeed(build);
    }
}
