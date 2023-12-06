package com.rany.cake.devops.ssh.base;

import com.rany.cake.devops.base.api.service.HostService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * dubbo依赖
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 16:00
 * @email 18668485565163.com
 */
@Configuration
public class DubboConfig {
    @Reference(check = false)
    private HostService hostService;

    @Bean
    public HostService accountFacade() {
        return hostService;
    }
}
