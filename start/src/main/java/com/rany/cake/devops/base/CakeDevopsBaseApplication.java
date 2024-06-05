package com.rany.cake.devops.base;

import com.cake.framework.mybatis.ParamLimitInterceptor;
import com.rany.cake.dingtalk.starter.EnableCakeSso;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Cake devops
 *
 * @author zhongshengwang
 */
@EnableCakeSso
@EnableDubbo
@EnableTransactionManagement
@EnableAspectJAutoProxy
@SpringBootApplication(exclude = {ParamLimitInterceptor.class})
@ImportResource(locations = {"classpath:config/spring-*.xml"})
@MapperScan(basePackages = {"com.rany.cake.devops.base.infra.dao", "com.rany.cake.devops.base.infra.mapper"})
@ComponentScan({"com.rany.cake.devops.base", "com.rany.cake.devops.base.domain", "com.rany.cake.devops.base.web", "com.rany.cake.devops.base.service", "com.rany.cake.devops.base.infra"})
public class CakeDevopsBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CakeDevopsBaseApplication.class, args);
    }

}
