package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.app.CreateAppCommand;
import com.rany.cake.devops.base.api.command.app.CreateAppEnvCommand;
import com.rany.cake.devops.base.api.dto.AppEnvDTO;
import com.rany.cake.devops.base.api.query.AppEnvQuery;
import com.rany.cake.devops.base.api.service.AppService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 应用
 */
@RestController
@RequestMapping("/app")
public class AppController {

    @Resource
    private AppService appService;


    @PostMapping("/create")
    public PojoResult<Long> createApp(CreateAppCommand createAppCommand) {
        return appService.createApp(createAppCommand);
    }

    @PostMapping("/createEnv")
    public PojoResult<Long> createAppEnv(CreateAppEnvCommand createAppEnvCommand) {
        return appService.createAppEnv(createAppEnvCommand);
    }

    @PostMapping("/listEnv")
    public ListResult<AppEnvDTO> createAppEnv(AppEnvQuery appEnvQuery) {
        return appService.listAppEnv(appEnvQuery);
    }
}
