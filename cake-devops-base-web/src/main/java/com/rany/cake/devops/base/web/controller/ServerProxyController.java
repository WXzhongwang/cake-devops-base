package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.proxy.CreateServerProxyCommand;
import com.rany.cake.devops.base.api.command.proxy.DeleteServerProxyCommand;
import com.rany.cake.devops.base.api.command.proxy.ModifyServerProxyCommand;
import com.rany.cake.devops.base.api.dto.ServerProxyDTO;
import com.rany.cake.devops.base.api.query.ServerProxyBasicQuery;
import com.rany.cake.devops.base.api.query.ServerProxyPageQuery;
import com.rany.cake.devops.base.api.service.ServerProxyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 代理配置
 */
@RestController
@RequestMapping("/api/devops/server-proxy")
public class ServerProxyController {

    @Resource
    private ServerProxyService serverProxyService;

    @PostMapping("/create")
    public PojoResult<String> create(@RequestBody CreateServerProxyCommand command) {
        return serverProxyService.createServerProxy(command);
    }

    @GetMapping("/get")
    public PojoResult<ServerProxyDTO> get(@RequestParam("id") Long proxyId) {
        ServerProxyBasicQuery serverProxyBasicQuery = new ServerProxyBasicQuery();
        serverProxyBasicQuery.setServerProxyId(proxyId);
        return serverProxyService.getServerProxy(serverProxyBasicQuery);
    }

    @PostMapping("/update")
    public PojoResult<Boolean> update(@RequestBody ModifyServerProxyCommand command) {
        return serverProxyService.modifyServerProxy(command);
    }

    @PostMapping("/delete")
    public PojoResult<Boolean> delete(@RequestBody DeleteServerProxyCommand command) {
        return serverProxyService.deleteServerProxy(command);
    }

    @PostMapping("/page")
    public PageResult<ServerProxyDTO> page(@RequestBody ServerProxyPageQuery serverProxyPageQuery) {
        return serverProxyService.pageServerProxy(serverProxyPageQuery);
    }
}
