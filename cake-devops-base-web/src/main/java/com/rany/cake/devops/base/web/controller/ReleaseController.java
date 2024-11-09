package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.release.CloseReleaseCommand;
import com.rany.cake.devops.base.api.command.release.CreateReleaseCommand;
import com.rany.cake.devops.base.api.command.release.DeployCommand;
import com.rany.cake.devops.base.api.dto.DeployHistoryDTO;
import com.rany.cake.devops.base.api.dto.DeployLogDTO;
import com.rany.cake.devops.base.api.dto.ReleaseDTO;
import com.rany.cake.devops.base.api.query.release.DeployHistoryPageQuery;
import com.rany.cake.devops.base.api.query.release.ReleasePageQuery;
import com.rany.cake.devops.base.api.service.DeployHistoryService;
import com.rany.cake.devops.base.api.service.ReleaseService;
import com.rany.cake.toolkit.lang.constant.Const;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/api/devops/release")
public class ReleaseController {
    @Resource
    private ReleaseService releaseService;

    @Resource
    private DeployHistoryService deployHistoryService;

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

    @PostMapping("/page-deploy-history")
    public PageResult<DeployHistoryDTO> pageDeployHistory(@RequestBody DeployHistoryPageQuery deployHistoryPageQuery) {
        return PageResult.succeed(deployHistoryService.queryDeployHistory(deployHistoryPageQuery));
    }

    @GetMapping("/query-deploy-log")
    public ListResult<DeployLogDTO> queryDeployLog(@RequestParam("pipeKey") String pipeKey) {
        log.info("查询日志 pipeKey:{}", pipeKey);
        // 获取当前时间戳，默认获取最近90天的日志
        long from = (System.currentTimeMillis() - Const.MS_S_60 * 60 * 24 * 90L) / 1000;
        long to = System.currentTimeMillis() / 1000;
        return ListResult.succeed(deployHistoryService.queryDeployLog(pipeKey, from, to));
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
