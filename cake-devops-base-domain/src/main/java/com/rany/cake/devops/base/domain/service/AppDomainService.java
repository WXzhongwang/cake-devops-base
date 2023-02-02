package com.rany.cake.devops.base.domain.service;

import com.rany.cake.devops.base.domain.aggegrate.App;
import com.rany.cake.devops.base.domain.repository.AppRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/28 20:59
 * @email 18668485565163.com
 */
@Component
@AllArgsConstructor
public class AppDomainService {

    private final AppRepository appRepository;
    
    /**
     * 创建应用
     *
     * @param app
     */
    public void createApp(App app) {
        appRepository.save(app);
    }
}
