package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.group.CreateGroupCommand;
import com.rany.cake.devops.base.api.command.group.ModifyGroupCommand;
import com.rany.cake.devops.base.api.dto.HostGroupDTO;
import com.rany.cake.devops.base.api.dto.HostGroupTreeDTO;
import com.rany.cake.devops.base.api.query.HostGroupBasicQuery;
import com.rany.cake.devops.base.api.query.HostGroupTreeQuery;
import com.rany.cake.devops.base.api.service.HostGroupService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 应用
 */
@RestController
@RequestMapping("/api/devops/host-group")
public class HostGroupController {

    @Resource
    private HostGroupService hostGroupService;

    @PostMapping("/tree")
    public ListResult<HostGroupTreeDTO> listGroupTree(@RequestBody(required = false) HostGroupTreeQuery treeQuery) {
        return hostGroupService.getHostGroupTree(treeQuery);
    }

    @PostMapping("/createGroup")
    public PojoResult<String> createHostGroup(@RequestBody CreateGroupCommand command) {
        return hostGroupService.createHostGroup(command);
    }

    @GetMapping("/getGroup")
    public PojoResult<HostGroupDTO> getGroup(@RequestParam("id") String hostGroupId) {
        HostGroupBasicQuery hostGroupBasicQuery = new HostGroupBasicQuery();
        hostGroupBasicQuery.setHostGroupId(hostGroupId);
        return hostGroupService.getHostGroup(hostGroupBasicQuery);
    }

    @GetMapping("/updateGroup")
    public PojoResult<Boolean> updateGroup(@RequestBody ModifyGroupCommand command) {
        return hostGroupService.modifyHostGroup(command);
    }
}
