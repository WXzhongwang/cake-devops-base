package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.app.CreateAppCommand;
import com.rany.cake.devops.base.api.command.app.CreateAppEnvCommand;
import com.rany.cake.devops.base.api.command.member.AddAppMemberCommand;
import com.rany.cake.devops.base.api.command.member.DeleteAppMemberCommand;
import com.rany.cake.devops.base.api.command.member.UpdateAppMemberCommand;
import com.rany.cake.devops.base.api.dto.AppDTO;
import com.rany.cake.devops.base.api.dto.AppEnvDTO;
import com.rany.cake.devops.base.api.dto.AppMemberDTO;
import com.rany.cake.devops.base.api.dto.DepartmentDTO;
import com.rany.cake.devops.base.api.query.app.*;
import com.rany.cake.devops.base.api.service.AppMemberService;
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
    @Resource
    private AppMemberService appMemberService;

    @PostMapping("/pageApp")
    public PageResult<AppDTO> pageApp(@RequestBody AppPageQuery appPageQuery) {
        return PageResult.succeed(appService.pageApp(appPageQuery));
    }

    @GetMapping("/getApp")
    public PojoResult<AppDTO> getApp(@RequestParam("id") String appId) {
        AppBasicQuery appBasicQuery = new AppBasicQuery();
        appBasicQuery.setAppId(appId);
        return PojoResult.succeed(appService.getApp(appBasicQuery));
    }

    @PostMapping("/createApp")
    public PojoResult<String> createApp(@RequestBody CreateAppCommand createAppCommand) {
        return PojoResult.succeed(appService.createApp(createAppCommand));
    }

    @PostMapping("/createEnv")
    public PojoResult<String> createAppEnv(@RequestBody CreateAppEnvCommand createAppEnvCommand) {
        return PojoResult.succeed(appService.createAppEnv(createAppEnvCommand));
    }

    @PostMapping("/getAppEnv")
    public PojoResult<AppEnvDTO> getAppEnv(@RequestBody AppEnvBasicQuery appEnvBasicQuery) {
        return PojoResult.succeed(appService.getAppEnv(appEnvBasicQuery));
    }

    @PostMapping("/listEnv")
    public ListResult<AppEnvDTO> listAppEnv(@RequestBody AppEnvQuery appEnvQuery) {
        return ListResult.succeed(appService.listAppEnv(appEnvQuery));
    }

    @GetMapping("/getDepartments")
    public ListResult<DepartmentDTO> getDepartments() {
        return ListResult.succeed(appService.listDepartments());
    }

    @PostMapping("/pageAppMembers")
    public PageResult<AppMemberDTO> pageAppMembers(@RequestBody AppMemberPageQuery appMemberPageQuery) {
        return PageResult.succeed(appMemberService.pageAppMembers(appMemberPageQuery));
    }

    @PostMapping("/addMember")
    public PojoResult<Boolean> addMember(@RequestBody AddAppMemberCommand addAppMemberCommand) {
        return PojoResult.succeed(appMemberService.addMember(addAppMemberCommand));
    }

    @PostMapping("/updateMember")
    public PojoResult<Boolean> updateMember(@RequestBody UpdateAppMemberCommand updateAppMemberCommand) {
        return PojoResult.succeed(appMemberService.updateMember(updateAppMemberCommand));
    }

    @PostMapping("/deleteMember")
    public PojoResult<Boolean> deleteMember(@RequestBody DeleteAppMemberCommand deleteAppMemberCommand) {
        return PojoResult.succeed(appMemberService.deleteMember(deleteAppMemberCommand));
    }
}
