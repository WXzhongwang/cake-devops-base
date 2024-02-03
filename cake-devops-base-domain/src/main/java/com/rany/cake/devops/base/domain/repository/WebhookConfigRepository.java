package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.entity.WebhookConfig;
import com.rany.cake.devops.base.domain.repository.param.WebhookConfigQueryParam;

public interface WebhookConfigRepository {
    WebhookConfig find(Long id);

    void remove(WebhookConfig env);

    void save(WebhookConfig env);

    void update(WebhookConfig env);

    Page<WebhookConfig> page(WebhookConfigQueryParam queryParam);
}
