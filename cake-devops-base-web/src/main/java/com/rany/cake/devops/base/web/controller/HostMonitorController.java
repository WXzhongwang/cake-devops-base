package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.monitor.InstallMonitorAgentCommand;
import com.rany.cake.devops.base.api.command.monitor.SyncMonitorAgentCommand;
import com.rany.cake.devops.base.api.command.monitor.TestConnectMonitorAgentCommand;
import com.rany.cake.devops.base.api.command.monitor.UpdateMonitorAgentCommand;
import com.rany.cake.devops.base.api.dto.HostMonitorDTO;
import com.rany.cake.devops.base.api.query.HostMonitorPageQuery;
import com.rany.cake.devops.base.api.service.HostMonitorService;
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
        return hostMonitorService.pageHostMonitor(query);
    }

    @GetMapping("/get")
    @ApiOperation(value = "查询监控配置")
    public PojoResult<HostMonitorDTO> getMonitorConfig(@RequestParam String hostId) {
        return hostMonitorService.findByHostId(hostId);
    }

    @GetMapping("/update")
    @ApiOperation(value = "查询监控配置")
    public PojoResult<Boolean> updateMonitorConfig(@RequestBody UpdateMonitorAgentCommand hostId) {
        return hostMonitorService.updateMonitorConfig(hostId);
    }

    @GetMapping("/test-connect")
    @ApiOperation(value = "测试连通性")
    public PojoResult<String> testConnect(@RequestBody TestConnectMonitorAgentCommand command) {
        String url = Valid.notBlank(command.getUrl());
        String accessToken = Valid.notBlank(command.getAccessToken());
        return hostMonitorService.getMonitorVersion(url, accessToken);
    }

    @GetMapping("/sync")
    @ApiOperation(value = "同步机器插件信息")
    public PojoResult<String> syncMonitorAgent(@RequestBody SyncMonitorAgentCommand command) {
        String url = Valid.notBlank(command.getUrl());
        String accessToken = Valid.notBlank(command.getAccessToken());
        return hostMonitorService.syncAgent(command);
    }

    @GetMapping("/install")
    @ApiOperation(value = "安装机器插件")
    public PojoResult<Boolean> installAgent(@RequestBody InstallMonitorAgentCommand command) {
        return hostMonitorService.installAgent(command);
    }

    @GetMapping("/check")
    @ApiOperation(value = "检查监控插件状态")
    public PojoResult<HostMonitorDTO> checkMonitorStatus(@RequestParam String hostId) {
        return hostMonitorService.checkMonitorStatus(hostId);
    }
}
