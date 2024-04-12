package com.rany.cake.devops.plugin.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.rany.cake.devops.plugin.constant.Const;
import com.rany.cake.devops.plugin.constant.PropertiesConst;
import com.rany.cake.toolkit.lang.utils.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * swagger 配置
 * <p>
 * <a href="http://localhost:9220/doc.html">...</a>
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/7/8 10:13
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Profile({"dev"})
public class SwaggerConfig {

    @Value("${agent.access.header}")
    private String accessHeader;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.getApiInfo())
                .securitySchemes(this.getSecuritySchemes())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rany.cake.devops.plugin.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 配置 api 信息
     *
     * @return api info
     */
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("machine-monitor-agent restful API")
                .contact(new Contact(Const.AUTHOR, Const.AUTHOR_GITHUB, Const.AUTHOR_EMAIL))
                .version(PropertiesConst.AGENT_VERSION)
                .description("机器监控agent端api管理")
                .build();
    }

    /**
     * 认证配置
     *
     * @return security scheme
     */
    private List<SecurityScheme> getSecuritySchemes() {
        ApiKey loginToken = new ApiKey(accessHeader, accessHeader, "header");
        return Lists.of(loginToken);
    }

}
