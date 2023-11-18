package com.rany.cake.devops.base.domain.base;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/2/2 20:28
 * @email 18668485565163.com
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "devops.app")
public class AppConfig {
    private Long tenantId;
}
