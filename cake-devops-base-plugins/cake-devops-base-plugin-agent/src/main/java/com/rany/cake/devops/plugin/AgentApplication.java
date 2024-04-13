package com.rany.cake.devops.plugin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author zhongshengwang
 */
@ImportResource(locations = {"classpath:config/spring-common.xml"})
@SpringBootApplication(scanBasePackages = "com.rany.cake.devops.plugin")
public class AgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgentApplication.class, args);
    }

}
