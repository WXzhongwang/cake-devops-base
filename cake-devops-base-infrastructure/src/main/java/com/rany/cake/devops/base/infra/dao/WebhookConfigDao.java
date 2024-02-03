package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.repository.param.WebhookConfigQueryParam;
import com.rany.cake.devops.base.infra.po.WebhookConfigPO;

import java.util.List;

public interface WebhookConfigDao {


    List<WebhookConfigPO> queryWebHookConfig(WebhookConfigQueryParam webhookConfigQueryParam);

}
