package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class WebHookConfigDTO extends DTO {

    private Long id;
    private String webhookName;
    private String webhookUrl;
    private String webhookType;
    private String webhookConfig;
}
