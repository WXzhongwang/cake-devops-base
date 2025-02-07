package com.rany.cake.devops.base;

import com.cake.framework.mybatis.ParamLimitInterceptor;
import com.rany.cake.dingtalk.starter.EnableCakeSso;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketMessagingAutoConfiguration;
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
@SpringBootApplication(scanBasePackages = {"com.rany.cake.devops.base"}, exclude = {ParamLimitInterceptor.class,
        WebSocketMessagingAutoConfiguration.class
})
@ImportResource(locations = {"classpath:config/spring-*.xml"})
@MapperScan(basePackages = {"com.rany.cake.devops.base.infra.dao", "com.rany.cake.devops.base.infra.mapper"})
//@PropertySource(name = "k8s-app", value = "cake-devops-base", factory = CakePropertySourceFactory.class)
public class CakeDevopsBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CakeDevopsBaseApplication.class, args);
    }
}
