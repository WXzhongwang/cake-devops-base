package com.rany.cake.devops.base.service;

import com.rany.uic.api.facade.account.AccountFacade;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 16:00
 * @email 18668485565163.com
 */
@Configuration
public class DubboConfig {

    // @Reference(check = false)
    @Reference
    private AccountFacade accountFacade;

    @Bean
    public AccountFacade accountFacade() {
        return accountFacade;
    }
}
