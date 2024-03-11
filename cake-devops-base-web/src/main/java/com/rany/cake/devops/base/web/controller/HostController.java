package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.host.CopyHostCommand;
import com.rany.cake.devops.base.api.command.host.CreateHostCommand;
import com.rany.cake.devops.base.api.command.host.ModifyHostCommand;
import com.rany.cake.devops.base.api.command.host.PingHostCommand;
import com.rany.cake.devops.base.api.dto.HostDTO;
import com.rany.cake.devops.base.api.query.HostBasicQuery;
import com.rany.cake.devops.base.api.query.HostPageQuery;
import com.rany.cake.devops.base.api.service.HostService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 主机
 */
@RestController
@RequestMapping("/api/devops/host")
public class HostController {

    @Resource
    private HostService hostService;

    @PostMapping("/pageHost")
    public PageResult<HostDTO> pageHost(@RequestBody(required = false) HostPageQuery hostPageQuery) {
        return hostService.pageHost(hostPageQuery);
    }

    @GetMapping("/getHost")
    public PojoResult<HostDTO> getHost(@RequestParam("id") String hostId) {
        HostBasicQuery hostBasicQuery = new HostBasicQuery();
        hostBasicQuery.setHostId(hostId);
        return hostService.getHost(hostBasicQuery);
    }

    @PostMapping("/createHost")
    public PojoResult<String> createHost(@RequestBody CreateHostCommand createHostCommand) {
        return hostService.createHost(createHostCommand);
    }

    @PostMapping("/updateHost")
    public PojoResult<Boolean> updateHost(@RequestBody ModifyHostCommand modifyHostCommand) {
        return hostService.modifyHost(modifyHostCommand);
    }

    @PostMapping("/copy")
    public PojoResult<String> copy(@RequestBody CopyHostCommand copyHostCommand) {
        return hostService.copyHost(copyHostCommand);
    }

    @PostMapping("/ping")
    public PojoResult<String> ping(@RequestBody PingHostCommand pingHostCommand) {
        return hostService.ping(pingHostCommand);
    }
}
