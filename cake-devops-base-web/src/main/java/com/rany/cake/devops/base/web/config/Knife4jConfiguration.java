package com.rany.cake.devops.base.web.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author tutu
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "swagger", value = "enabled", matchIfMissing = true)
public class Knife4jConfiguration {

    @Bean
    public Docket docket() {
        // 联系人信息
        Contact contact = new Contact("CakeDevopsAdmin", "https://github.com/WXzhongwang", "1528683621@qq.com");
        // 创建 Docket
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("CAKE DEVOPS")
                        .description("DEVOPS")
                        .termsOfServiceUrl("http://127.0.0.1:8888")
                        .contact(contact)
                        .version("1.0")
                        .build())
                .groupName("1.x")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rany.cake.devops.base.web.controller"))
                .paths(PathSelectors.any())
                .build();

    }
}
