package com.rany.cake.devops.plugin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhongshengwang
 */
@SpringBootApplication(scanBasePackages = "com.rany.cake.devops.plugin")
public class AgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgentApplication.class, args);
    }

}
