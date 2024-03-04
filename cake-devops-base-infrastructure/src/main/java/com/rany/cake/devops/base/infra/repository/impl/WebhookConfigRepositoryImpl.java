package com.rany.cake.devops.base.infra.repository.impl;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.cake.devops.base.domain.entity.WebhookConfig;
import com.rany.cake.devops.base.domain.repository.WebhookConfigRepository;
import com.rany.cake.devops.base.domain.repository.param.WebhookConfigQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.infra.aop.PagingQuery;
import com.rany.cake.devops.base.infra.convertor.WebhookConfigDataConvertor;
import com.rany.cake.devops.base.infra.dao.WebhookConfigDao;
import com.rany.cake.devops.base.infra.mapper.WebhookConfigPOMapper;
import com.rany.cake.devops.base.infra.po.WebhookConfigPO;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebhookConfigRepositoryImpl implements WebhookConfigRepository {
    private WebhookConfigPOMapper webhookConfigPOMapper;
    private WebhookConfigDao webhookConfigDAO;
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

    @Override
    public void update(WebhookConfig env) {
        WebhookConfigPO webhookConfigPO = webhookConfigDataConvertor.sourceToTarget(env);
        webhookConfigPOMapper.updateByPrimaryKey(webhookConfigPO);
    }

    @Override
    @PagingQuery
    public Page<WebhookConfig> page(WebhookConfigQueryParam queryParam) {
        List<WebhookConfigPO> webhookConfigs = webhookConfigDAO.queryWebHookConfig(queryParam);
        PageInfo<WebhookConfigPO> pageInfo = new PageInfo<>(webhookConfigs);
        List<WebhookConfig> configs = webhookConfigDataConvertor.targetToSource(webhookConfigs);
        return PageUtils.build(pageInfo, configs);
    }
}
