package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.release.CreateReleaseCommand;
import com.rany.cake.devops.base.api.command.release.DeployCommand;
import com.rany.cake.devops.base.api.service.ReleaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/release")
public class ReleaseController {
    @Resource
    private ReleaseService releaseService;

    /**
     * 创建变更单
     *
     * @param createReleaseCommand 创建变更单
     * @return 成功
     */
    @PostMapping("/create")
    public PojoResult<Boolean> createRelease(CreateReleaseCommand createReleaseCommand) {
        return releaseService.createRelease(createReleaseCommand);
    }

    /**
     * 基于变更单发布
     *
     * @param deployCommand 基于变更单发布
     * @return 成功
     */
    @PostMapping("/deploy")
    public PojoResult<Boolean> deploy(DeployCommand deployCommand) {
        return releaseService.deploy(deployCommand);
    }
}