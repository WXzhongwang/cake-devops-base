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
@MapperScan(basePackages = {"com.rany.cake.devops.base.infra.dao", "com.rany.cake.devops.base.infra.mapper"})
@ComponentScan({"com.rany.cake.devops.base.domain", "com.rany.cake.devops.base.web", "com.rany.cake.devops.base.service", "com.rany.cake.devops.base.infra"})
public class CakeDevopsBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CakeDevopsBaseApplication.class, args);
    }

}
