package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.system.CreateSystemEnvCommand;
import com.rany.cake.devops.base.api.command.system.DeleteSystemEnvCommand;
import com.rany.cake.devops.base.api.command.system.ModifySystemEnvCommand;
import com.rany.cake.devops.base.api.dto.SystemEnvDTO;
import com.rany.cake.devops.base.api.query.SystemEnvBasicQuery;
import com.rany.cake.devops.base.api.query.SystemEnvPageQuery;
import com.rany.cake.devops.base.api.service.SystemEnvService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 主机环境变量
 */
@RestController
@RequestMapping("/api/devops/system-env")
public class SystemEnvController {

    @Resource
    private SystemEnvService hostEnvService;

    @PostMapping("/page")
    public PageResult<SystemEnvDTO> pageSystemEnv(@RequestBody(required = false) SystemEnvPageQuery hostPageQuery) {
        return hostEnvService.pageSystemEnv(hostPageQuery);
    }

    @GetMapping("/get")
    public PojoResult<SystemEnvDTO> getSystemEnv(@RequestParam("id") Long id) {
        SystemEnvBasicQuery hostBasicQuery = new SystemEnvBasicQuery();
        hostBasicQuery.setId(id);
        return hostEnvService.getSystemEnv(hostBasicQuery);
    }

    @PostMapping("/save-map")
    public PojoResult<Void> saveMap(@RequestBody Map<String, String> envMap) {
        return hostEnvService.saveEnv(envMap);
    }

    @PostMapping("/create")
    public PojoResult<String> createSystemEnv(@RequestBody CreateSystemEnvCommand createSystemEnvCommand) {
        return hostEnvService.createSystemEnv(createSystemEnvCommand);
    }

    @PostMapping("/update")
    public PojoResult<Boolean> updateSystemEnv(@RequestBody ModifySystemEnvCommand modifySystemCommand) {
        return hostEnvService.modifySystemEnv(modifySystemCommand);
    }

    @PostMapping("/delete")
    public PojoResult<Boolean> deleteSystemEnv(@RequestBody DeleteSystemEnvCommand deleteSystemEnvCommand) {
        return hostEnvService.deleteSystemEnv(deleteSystemEnvCommand);
    }
}
