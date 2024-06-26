package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.release.CloseReleaseCommand;
import com.rany.cake.devops.base.api.command.release.CreateReleaseCommand;
import com.rany.cake.devops.base.api.command.release.DeployCommand;
import com.rany.cake.devops.base.api.dto.ReleaseDTO;
import com.rany.cake.devops.base.api.query.release.ReleasePageQuery;
import com.rany.cake.devops.base.api.service.ReleaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/devops/release")
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
    public PojoResult<Boolean> createRelease(@RequestBody CreateReleaseCommand createReleaseCommand) {
        return PojoResult.succeed(releaseService.createRelease(createReleaseCommand));
    }

    @PostMapping("/page")
    public PageResult<ReleaseDTO> pageRelease(@RequestBody ReleasePageQuery releasePageQuery) {
        return PageResult.succeed(releaseService.pageRelease(releasePageQuery));
    }

    /**
     * 基于变更单发布
     *
     * @param deployCommand 基于变更单发布
     * @return 成功
     */
    @PostMapping("/deploy")
    public PojoResult<Boolean> deploy(@RequestBody DeployCommand deployCommand) {
        return PojoResult.succeed(releaseService.deploy(deployCommand));
    }

    /**
     * 关闭变更单发布
     *
     * @param command 基于变更单发布
     * @return 成功
     */
    @PostMapping("/close")
    public PojoResult<Boolean> close(@RequestBody CloseReleaseCommand command) {
        return PojoResult.succeed(releaseService.close(command));
    }
}
