package com.rany.cake.devops.base.domain.base;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "devops.cr")
public class CrConfig {
    private String channel;
    private HarborConf harbor;
    private AliyunConf aliyun;
    private SonarConf sonar;


    @Data
    public static class HarborConf {
        private String harborUrl;
        private String harborUser;
        private String harborPassword;
        private Long defaultProjectId;
    }

    @Data
    public static class SonarConf {
        private String url;
        private String token;
    }


    @Data
    public static class AliyunConf {
        /**
         * acr
         */
        private String instanceId;
        private String region;
        private String namespace;
        private String accessKey;
        private String secretKey;
        // docker 登录使用
        private String host;
        private String username;
        private String password;
    }

    public String getHost() {
        if (StringUtils.equals(channel, "aliyun")) {
            return aliyun.getHost();
        }
        return harbor.getHarborUrl();
    }

    public String getNameSpace() {
        if (StringUtils.equals(channel, "aliyun")) {
            return aliyun.getNamespace();
        }
        return String.valueOf(harbor.getDefaultProjectId());
    }

    public String getShellName() {
        if (StringUtils.equals(channel, "aliyun")) {
            return "push_aliyun.sh";
        }
        return "push_harbor.sh";
    }
}
