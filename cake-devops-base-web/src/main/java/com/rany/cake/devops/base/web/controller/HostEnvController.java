package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.host.env.*;
import com.rany.cake.devops.base.api.dto.HostEnvDTO;
import com.rany.cake.devops.base.api.query.HostEnvBasicQuery;
import com.rany.cake.devops.base.api.query.HostEnvPageQuery;
import com.rany.cake.devops.base.api.query.HostEnvViewQuery;
import com.rany.cake.devops.base.api.service.HostEnvService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 主机环境变量
 */
@RestController
@RequestMapping("/api/devops/host-env")
public class HostEnvController {

    @Resource
    private HostEnvService hostEnvService;

    @PostMapping("/page")
    public PageResult<HostEnvDTO> pageHostEnv(@RequestBody(required = false) HostEnvPageQuery hostPageQuery) {
        return PageResult.succeed(hostEnvService.pageHostEnv(hostPageQuery));
    }

    @GetMapping("/get")
    public PojoResult<HostEnvDTO> getHostEnv(@RequestParam("id") Long envId) {
        HostEnvBasicQuery hostBasicQuery = new HostEnvBasicQuery();
        hostBasicQuery.setEnvId(envId);
        return PojoResult.succeed(hostEnvService.getHostEnv(hostBasicQuery));
    }

    @PostMapping("/create")
    public PojoResult<String> createHostEnv(@RequestBody CreateHostEnvCommand createHostEnvCommand) {
        return PojoResult.succeed(hostEnvService.createHostEnv(createHostEnvCommand));
    }

    @PostMapping("/update")
    public PojoResult<Boolean> updateHostEnv(@RequestBody ModifyHostEnvCommand modifyHostCommand) {
        return PojoResult.succeed(hostEnvService.modifyHostEnv(modifyHostCommand));
    }

    @PostMapping("/delete")
    public PojoResult<Boolean> deleteHostEnv(@RequestBody DeleteHostEnvCommand deleteHostEnvCommand) {
        return PojoResult.succeed(hostEnvService.deleteHostEnv(deleteHostEnvCommand));
    }

    @PostMapping("/async")
    public PojoResult<Boolean> asyncHostEnv(@RequestBody AsyncHostEnvCommand asyncHostEnvCommand) {
        return PojoResult.succeed(hostEnvService.asyncHostEnv(asyncHostEnvCommand));
    }

    @PostMapping("/view")
    public PojoResult<String> view(@RequestBody HostEnvViewQuery hostEnvViewQuery) {
        return PojoResult.succeed(hostEnvService.view(hostEnvViewQuery));
    }

    @PostMapping("/saveView")
    public PojoResult<String> saveView(@RequestBody HostEnvViewSaveCommand saveCommand) {
        return PojoResult.succeed(hostEnvService.saveView(saveCommand));
    }
}
