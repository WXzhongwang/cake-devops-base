package com.rany.cake.devops.base.web.config;

import com.rany.cake.toolkit.cms.client.CmsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CmsConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public CmsClient cmsClient(@Value("${cms.env}") String env,
                               @Value("${cms.oss.endpoint}") String endpoint,
                               @Value("${cms.site.name}") String siteName) {
        CmsClient cmsClient = new CmsClient();
        cmsClient.setAppName(siteName);
        cmsClient.setOssBucket(endpoint);
        cmsClient.setEnv(env);
        return cmsClient;
    }
}
