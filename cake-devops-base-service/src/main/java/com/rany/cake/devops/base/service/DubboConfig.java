package com.rany.cake.devops.base.service;

import com.rany.ops.api.facade.account.AccountFacade;
import com.rany.ops.api.facade.application.ApplicationFacade;
import com.rany.ops.api.facade.grant.RbacQueryFacade;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * dubbo依赖
 *
 * @author zhongshengwang
 * @description dubbo reference config
 * @date 2023/1/15 16:00
 * @email 18668485565163.com
 */
@Configuration
public class DubboConfig {
    @Reference(check = false)
    private AccountFacade accountFacade;

    @Reference(check = false)
    private ApplicationFacade applicationFacade;

    @Reference(check = false)
    private RbacQueryFacade rbacQueryFacade;

    @Bean
    public AccountFacade accountFacade() {
        return accountFacade;
    }

    @Bean
    public ApplicationFacade applicationFacade() {
        return applicationFacade;
    }

    @Bean
    public RbacQueryFacade rbacQueryFacade() {
        return rbacQueryFacade;
    }
}
