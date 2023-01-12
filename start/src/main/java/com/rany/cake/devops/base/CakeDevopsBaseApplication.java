package com.rany.cake.devops.base;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Cake devops
 * @author zhongshengwang
 */
@EnableDubbo
@EnableTransactionManagement
@SpringBootApplication
public class CakeDevopsBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CakeDevopsBaseApplication.class, args);
    }

}
