package com.rany.cake.devops.base.web.controller;


import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.account.CreateServerAccountCommand;
import com.rany.cake.devops.base.api.command.account.DeleteServerAccountCommand;
import com.rany.cake.devops.base.api.command.account.ModifyServerAccountCommand;
import com.rany.cake.devops.base.api.dto.ServerAccountDTO;
import com.rany.cake.devops.base.api.query.ServerAccountBasicQuery;
import com.rany.cake.devops.base.api.query.ServerAccountPageQuery;
import com.rany.cake.devops.base.api.service.ServerAccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/devops/server-account")
public class ServerAccountController {

    @Resource
    private ServerAccountService serverAccountService;

    @PostMapping("/create")
    public PojoResult<String> create(@RequestBody CreateServerAccountCommand command) {
        return serverAccountService.createServerAccount(command);
    }

    @GetMapping("/get")
    public PojoResult<ServerAccountDTO> get(@RequestParam("id") String serverAccountId) {
        ServerAccountBasicQuery serverAccountBasicQuery = new ServerAccountBasicQuery();
        serverAccountBasicQuery.setServerAccountId(serverAccountId);
        return serverAccountService.getServerAccount(serverAccountBasicQuery);
    }

    @PostMapping("/update")
    public PojoResult<Boolean> update(@RequestBody ModifyServerAccountCommand command) {
        return serverAccountService.modifyServerAccount(command);
    }

    @PostMapping("/delete")
    public PojoResult<Boolean> delete(@RequestBody DeleteServerAccountCommand command) {
        return serverAccountService.deleteServerAccount(command);
    }

    @PostMapping("/page")
    public PageResult<ServerAccountDTO> page(@RequestBody ServerAccountPageQuery serverAccountPageQuery) {
        return serverAccountService.pageServerAccount(serverAccountPageQuery);
    }
}
