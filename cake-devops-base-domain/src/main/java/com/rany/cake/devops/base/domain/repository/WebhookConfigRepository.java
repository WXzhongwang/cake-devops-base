package com.rany.cake.devops.base.domain.repository;

import com.rany.cake.devops.base.domain.entity.WebhookConfig;

public interface WebhookConfigRepository {
    WebhookConfig find(Long id);

    void remove(WebhookConfig env);

    void save(WebhookConfig env);
}
