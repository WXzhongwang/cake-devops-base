package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.app.*;
import com.rany.cake.devops.base.api.command.member.AddAppMemberCommand;
import com.rany.cake.devops.base.api.command.member.DeleteAppMemberCommand;
import com.rany.cake.devops.base.api.command.member.UpdateAppMemberCommand;
import com.rany.cake.devops.base.api.dto.*;
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

    @PostMapping("/page")
    public PageResult<AppDTO> pageApp(@RequestBody AppPageQuery appPageQuery) {
        return PageResult.succeed(appService.pageApp(appPageQuery));
    }

    @GetMapping("/get")
    public PojoResult<AppDTO> getApp(@RequestParam("id") String appId) {
        AppBasicQuery appBasicQuery = new AppBasicQuery();
        appBasicQuery.setAppId(appId);
        return PojoResult.succeed(appService.getApp(appBasicQuery));
    }

    @PostMapping("/create")
    public PojoResult<String> createApp(@RequestBody CreateAppCommand createAppCommand) {
        return PojoResult.succeed(appService.createApp(createAppCommand));
    }

    @PostMapping("/create-env")
    public PojoResult<String> createAppEnv(@RequestBody CreateAppEnvCommand createAppEnvCommand) {
        return PojoResult.succeed(appService.createAppEnv(createAppEnvCommand));
    }

    @PostMapping("/modify-config-map")
    public PojoResult<Boolean> modifyAppEnvConfigMap(@RequestBody ModifyConfigMapCommand modifyAppEnvConfigMapCommand) {
        return PojoResult.succeed(appService.modifyAppConfigMap(modifyAppEnvConfigMapCommand));
    }

    @PostMapping("/modify-env-vars")
    public PojoResult<Boolean> modifyAppEnvVars(@RequestBody ModifyEnvVarsCommand modifyEnvVarsCommand) {
        return PojoResult.succeed(appService.modifyAppEnvVars(modifyEnvVarsCommand));
    }

    @PostMapping("/modify-env-domains")
    public PojoResult<Boolean> modifyAppEnvDomains(@RequestBody ModifyAppEnvDomainCommand modifyAppEnvDomainCommand) {
        return PojoResult.succeed(appService.modifyAppEnvDomains(modifyAppEnvDomainCommand));
    }

    @PostMapping("/modify-env-resources")
    public PojoResult<Boolean> modifyAppEnvReSource(@RequestBody ModifyEnvResourceCommand modifyEnvResourceCommand) {
        return PojoResult.succeed(appService.modifyAppEnvResource(modifyEnvResourceCommand));
    }

    @PostMapping("/scale")
    public PojoResult<Boolean> scale(@RequestBody ScaleEnvCommand scaleEnvCommand) {
        return PojoResult.succeed(appService.scale(scaleEnvCommand));
    }


    @PostMapping("/list-app-pods")
    public ListResult<PodDTO> listAppPods(@RequestBody AppEnvPodQuery appEnvPodQuery) {
        return ListResult.succeed(appService.listAppEnvPod(appEnvPodQuery));
    }

    @PostMapping("/get-app-env")
    public PojoResult<AppEnvDTO> getAppEnv(@RequestBody AppEnvBasicQuery appEnvBasicQuery) {
        return PojoResult.succeed(appService.getAppEnv(appEnvBasicQuery));
    }

    @PostMapping("/list-env")
    public ListResult<AppEnvDTO> listAppEnv(@RequestBody AppEnvQuery appEnvQuery) {
        return ListResult.succeed(appService.listAppEnv(appEnvQuery));
    }

    @GetMapping("/departments")
    public ListResult<DepartmentDTO> getDepartments() {
        return ListResult.succeed(appService.listDepartments());
    }

    @PostMapping("/page-app-members")
    public PageResult<AppMemberDTO> pageAppMembers(@RequestBody AppMemberPageQuery appMemberPageQuery) {
        return PageResult.succeed(appMemberService.pageAppMembers(appMemberPageQuery));
    }

    @PostMapping("/add-member")
    public PojoResult<Boolean> addMember(@RequestBody AddAppMemberCommand addAppMemberCommand) {
        return PojoResult.succeed(appMemberService.addMember(addAppMemberCommand));
    }

    @PostMapping("/update-member")
    public PojoResult<Boolean> updateMember(@RequestBody UpdateAppMemberCommand updateAppMemberCommand) {
        return PojoResult.succeed(appMemberService.updateMember(updateAppMemberCommand));
    }

    @PostMapping("/delete-member")
    public PojoResult<Boolean> deleteMember(@RequestBody DeleteAppMemberCommand deleteAppMemberCommand) {
        return PojoResult.succeed(appMemberService.deleteMember(deleteAppMemberCommand));
    }
}
