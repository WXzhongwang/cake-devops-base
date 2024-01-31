package com.rany.cake.devops.base.web.controller;


import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.account.CreateServerKeyCommand;
import com.rany.cake.devops.base.api.command.account.DeleteServerKeyCommand;
import com.rany.cake.devops.base.api.command.account.ModifyServerKeyCommand;
import com.rany.cake.devops.base.api.dto.ServerKeyDTO;
import com.rany.cake.devops.base.api.query.ServerAccountBasicQuery;
import com.rany.cake.devops.base.api.query.ServerKeyPageQuery;
import com.rany.cake.devops.base.api.service.ServerKeyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/devops/server-key")
public class ServerKeyController {

    @Resource
    private ServerKeyService serverKeyService;

    @PostMapping("/create")
    public PojoResult<String> create(@RequestBody CreateServerKeyCommand command) {
        return serverKeyService.createServerKey(command);
    }

    @GetMapping("/get")
    public PojoResult<ServerKeyDTO> get(@RequestParam("id") String serverAccountId) {
        ServerAccountBasicQuery serverAccountBasicQuery = new ServerAccountBasicQuery();
        serverAccountBasicQuery.setServerAccountId(serverAccountId);
        return serverKeyService.getServerKey(serverAccountBasicQuery);
    }

    @PostMapping("/update")
    public PojoResult<Boolean> update(@RequestBody ModifyServerKeyCommand command) {
        return serverKeyService.modifyServerKey(command);
    }

    @PostMapping("/delete")
    public PojoResult<Boolean> delete(@RequestBody DeleteServerKeyCommand command) {
        return serverKeyService.deleteServerKey(command);
    }

    @PostMapping("/page")
    public PageResult<ServerKeyDTO> page(@RequestBody ServerKeyPageQuery serverKeyPageQuery) {
        return serverKeyService.pageServerKey(serverKeyPageQuery);
    }
}
