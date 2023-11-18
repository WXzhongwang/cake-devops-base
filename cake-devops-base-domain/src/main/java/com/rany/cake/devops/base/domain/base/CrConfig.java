package com.rany.cake.devops.base.domain.base;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "devops.cr")
public class CrConfig {
    private String channel;
    private HarborConf harbor;
    private AliyunConf aliyunConf;

    @Data
    public static class HarborConf {
        private String harborUrl;
        private String harborUser;
        private String harborPassword;
        private Long defaultProjectId;
    }

    @Data
    public static class AliyunConf {
        private String instanceId;
        private String region;
        private String accessKey;
        private String secretKey;
    }
}
