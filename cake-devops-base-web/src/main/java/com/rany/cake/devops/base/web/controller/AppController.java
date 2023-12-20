package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.app.CreateAppCommand;
import com.rany.cake.devops.base.api.command.app.CreateAppEnvCommand;
import com.rany.cake.devops.base.api.dto.AppDTO;
import com.rany.cake.devops.base.api.dto.AppEnvDTO;
import com.rany.cake.devops.base.api.dto.DepartmentDTO;
import com.rany.cake.devops.base.api.query.AppBasicQuery;
import com.rany.cake.devops.base.api.query.AppEnvQuery;
import com.rany.cake.devops.base.api.query.AppPageQuery;
import com.rany.cake.devops.base.api.service.AppService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 应用
 */
@RestController
@RequestMapping("/api/devops/app")
public class AppController {

    @Resource
    private AppService appService;

    @PostMapping("/pageApp")
    public PageResult<AppDTO> pageApp(@RequestBody AppPageQuery appPageQuery) {
        return appService.pageApp(appPageQuery);
    }

    @GetMapping("/getApp")
    public PojoResult<AppDTO> getApp(@RequestParam("id")String appId) {
        AppBasicQuery appBasicQuery = new AppBasicQuery();
        appBasicQuery.setAppId(appId);
        return appService.getApp(appBasicQuery);
    }

    @PostMapping("/createApp")
    public PojoResult<String> createApp(@RequestBody CreateAppCommand createAppCommand) {
        return appService.createApp(createAppCommand);
    }

    @PostMapping("/createEnv")
    public PojoResult<String> createAppEnv(@RequestBody CreateAppEnvCommand createAppEnvCommand) {
        return appService.createAppEnv(createAppEnvCommand);
    }

    @PostMapping("/listEnv")
    public ListResult<AppEnvDTO> listAppEnv(@RequestBody AppEnvQuery appEnvQuery) {
        return appService.listAppEnv(appEnvQuery);
    }

    @GetMapping("/getDepartments")
    public ListResult<DepartmentDTO> getDepartments() {
        return appService.listDepartments();
    }
}
