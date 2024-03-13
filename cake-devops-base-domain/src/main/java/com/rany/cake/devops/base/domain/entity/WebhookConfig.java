package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class WebhookConfig extends BaseEntity<Long> {

    private String webhookName;
    private String webhookUrl;
    private Integer webhookType;
    private String webhookConfig;
}
