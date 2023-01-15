package com.rany.cake.devops.base.domain.service;

import com.rany.uic.api.facade.account.AccountFacade;
import jdk.nashorn.internal.ir.annotations.Reference;
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


    @Reference
    private AccountFacade accountFacade;
    
    @Bean
    private AccountFacade accountFacade() {
        return accountFacade;
    }
}
