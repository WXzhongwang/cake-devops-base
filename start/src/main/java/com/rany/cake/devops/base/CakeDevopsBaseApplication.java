package com.rany.cake.devops.base;

import com.cake.framework.mybatis.ParamLimitInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Cake devops
 *
 * @author zhongshengwang
 */
@EnableTransactionManagement
@SpringBootApplication(exclude = {ParamLimitInterceptor.class})
@MapperScan(basePackages = {"com.rany.cake.devops.base.infra"})
@ComponentScan(basePackages = {"com.rany.cake.devops"})
public class CakeDevopsBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CakeDevopsBaseApplication.class, args);
    }

}
