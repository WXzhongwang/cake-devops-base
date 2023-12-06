package com.rany.cake.devops.ssh;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Cake devops
 *
 * @author zhongshengwang
 */
@EnableDubbo
@EnableAspectJAutoProxy
@ComponentScan({"com.rany.cake.devops.ssh"})
public class CakeDevopsSshApplication {

    public static void main(String[] args) {
        SpringApplication.run(CakeDevopsSshApplication.class, args);
    }

}
