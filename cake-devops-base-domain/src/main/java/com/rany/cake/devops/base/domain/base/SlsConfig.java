package com.rany.cake.devops.base.domain.base;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "devops.sls")
public class SlsConfig {
    private String endpoint;
    private String logstore;
    private String project;
    private String accessKeyId;
    private String accessKeySecret;
}
