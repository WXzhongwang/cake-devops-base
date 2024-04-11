package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.exception.BusinessException;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.agent.InstallMonitorAgentCommand;
import com.rany.cake.devops.base.api.command.agent.SyncMonitorAgentCommand;
import com.rany.cake.devops.base.api.command.agent.TestConnectMonitorAgentCommand;
import com.rany.cake.devops.base.api.command.agent.UpdateMonitorAgentCommand;
import com.rany.cake.devops.base.api.dto.HostMonitorDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.query.agent.HostMonitorPageQuery;
import com.rany.cake.devops.base.api.service.HostMonitorService;
import com.rany.cake.toolkit.lang.utils.Strings;
import com.rany.cake.toolkit.lang.utils.Valid;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/devops/host-monitor")
public class HostMonitorController {
    @Resource
    private HostMonitorService hostMonitorService;

    @PostMapping("/page")
    public PageResult<HostMonitorDTO> page(@RequestBody HostMonitorPageQuery query) {
        return PageResult.succeed(hostMonitorService.pageHostMonitor(query));
    }

    @GetMapping("/get")
    @ApiOperation(value = "查询监控配置")
    public PojoResult<HostMonitorDTO> getMonitorConfig(@RequestParam String hostId) {
        return PojoResult.succeed(hostMonitorService.findByHostId(hostId));
    }

    @PostMapping("/update")
    @ApiOperation(value = "查询监控配置")
    public PojoResult<Boolean> updateMonitorConfig(@RequestBody UpdateMonitorAgentCommand hostId) {
        return PojoResult.succeed(hostMonitorService.updateMonitorConfig(hostId));
    }

    @PostMapping("/test-connect")
    @ApiOperation(value = "测试连通性")
    public PojoResult<String> testConnect(@RequestBody TestConnectMonitorAgentCommand command) {
        String url = Valid.notBlank(command.getUrl());
        String accessToken = Valid.notBlank(command.getAccessToken());
        String monitorVersion = hostMonitorService.getMonitorVersion(url, accessToken);
        if (Strings.isEmpty(monitorVersion)) {
            throw new BusinessException(DevOpsErrorMessage.OPS_CONNECTED_ERROR);
        }
        return PojoResult.succeed();
    }

    @PostMapping("/sync")
    @ApiOperation(value = "同步机器插件信息")
    public PojoResult<String> syncMonitorAgent(@RequestBody SyncMonitorAgentCommand command) {
        String monitorVersion = hostMonitorService.syncAgent(command);
        if (Strings.isEmpty(monitorVersion)) {
            throw new BusinessException(DevOpsErrorMessage.OPS_CONNECTED_ERROR);
        }
        return PojoResult.succeed(monitorVersion);
    }

    @PostMapping("/install")
    @ApiOperation(value = "安装机器插件")
    public PojoResult<Boolean> installAgent(@RequestBody InstallMonitorAgentCommand command) {
        return PojoResult.succeed(hostMonitorService.installAgent(command));
    }

    @GetMapping("/check")
    @ApiOperation(value = "检查监控插件状态")
    public PojoResult<HostMonitorDTO> checkMonitorStatus(@RequestParam String hostId) {
        return PojoResult.succeed(hostMonitorService.checkMonitorStatus(hostId));
    }
}
