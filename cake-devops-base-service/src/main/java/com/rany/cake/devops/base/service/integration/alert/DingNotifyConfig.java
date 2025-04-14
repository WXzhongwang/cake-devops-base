package com.rany.cake.devops.base.service.integration.alert;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhongshengwang
 * @version 1.0
 * @date 2025/1/17 15:45
 * @slogon 找到银弹
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "devops.notify")
public class DingNotifyConfig {
    private String webhook;
    private String secret;
    private String monitorAddress;
    private Boolean sign;
}
