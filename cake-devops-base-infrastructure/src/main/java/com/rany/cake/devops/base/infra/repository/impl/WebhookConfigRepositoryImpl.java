package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.entity.WebhookConfig;
import com.rany.cake.devops.base.domain.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.domain.repository.WebhookConfigRepository;
import com.rany.cake.devops.base.infra.convertor.WebhookConfigDataConvertor;
import com.rany.cake.devops.base.infra.mapper.WebhookConfigPOMapper;
import com.rany.cake.devops.base.infra.po.WebhookConfigPO;
import org.springframework.stereotype.Service;

@Service
public class WebhookConfigRepositoryImpl implements WebhookConfigRepository {
    private WebhookConfigPOMapper webhookConfigPOMapper;
    private WebhookConfigDataConvertor webhookConfigDataConvertor;

    @Override
    public WebhookConfig find(Long id) {
        WebhookConfigPO webhookConfigPO = webhookConfigPOMapper.selectByPrimaryKey(id);
        return webhookConfigDataConvertor.targetToSource(webhookConfigPO);
    }

    @Override
    public void remove(WebhookConfig env) {
        WebhookConfigPO webhookConfigPO = webhookConfigPOMapper.selectByPrimaryKey(env.getId());
        webhookConfigPO.setIsDeleted(DeleteStatusEnum.YES.getValue());
        webhookConfigPOMapper.updateByPrimaryKey(webhookConfigPO);
    }

    @Override
    public void save(WebhookConfig env) {
        WebhookConfigPO webhookConfigPO = webhookConfigDataConvertor.sourceToTarget(env);
        webhookConfigPOMapper.insertSelective(webhookConfigPO);
    }
}
