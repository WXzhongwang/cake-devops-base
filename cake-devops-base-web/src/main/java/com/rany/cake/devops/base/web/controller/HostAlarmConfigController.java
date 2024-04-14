package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.host.alarm.ConfigureAlarmCommand;
import com.rany.cake.devops.base.api.command.host.alarm.SetHostAlarmConfigCommand;
import com.rany.cake.devops.base.api.command.host.alarm.SetHostAlarmGroupCommand;
import com.rany.cake.devops.base.api.dto.HostAlarmConfigDTO;
import com.rany.cake.devops.base.api.dto.HostAlarmConfigWrapperDTO;
import com.rany.cake.devops.base.api.dto.HostAlarmHistoryDTO;
import com.rany.cake.devops.base.api.query.alarm.HostAlarmHistoryPageQuery;
import com.rany.cake.devops.base.api.service.HostAlarmConfigService;
import com.rany.cake.devops.base.api.service.HostAlarmService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 主机告警配置
 *
 * @author zhongshengwang
 */
@RestController
@RequestMapping("/api/devops/host-alarm")
public class HostAlarmConfigController {

    @Resource
    private HostAlarmConfigService hostAlarmConfigService;
    @Resource
    private HostAlarmService hostAlarmService;


    @GetMapping("/get-config")
    public PojoResult<HostAlarmConfigWrapperDTO> getAlarmConfig(@RequestParam("hostId") String hostId) {
        return PojoResult.succeed(hostAlarmConfigService.getHostAlarmConfig(hostId));
    }

    @PostMapping("/configure")
    public PojoResult<Boolean> configure(@RequestBody ConfigureAlarmCommand command) {
        return PojoResult.succeed(hostAlarmConfigService.setHostAlarmConfig(command));
    }

    @PostMapping("/set-alarm-config")
    public PojoResult<Boolean> setAlarmConfig(@RequestBody SetHostAlarmConfigCommand command) {
        return PojoResult.succeed(hostAlarmConfigService.setHostAlarmConfig(command));
    }

    @PostMapping("/set-alarm-group")
    public PojoResult<Boolean> setAlarmHost(@RequestBody SetHostAlarmGroupCommand command) {
        return PojoResult.succeed(hostAlarmConfigService.setHostAlarmGroup(command));
    }

    @GetMapping("/get-config-list")
    public ListResult<HostAlarmConfigDTO> getAlarmConfigList(@RequestParam("hostId") String hostId) {
        return ListResult.succeed(hostAlarmConfigService.selectAlarmConfigByHostId(hostId));
    }

    @PostMapping("/history")
    public PageResult<HostAlarmHistoryDTO> pageHistory(@RequestBody HostAlarmHistoryPageQuery query) {
        return PageResult.succeed(hostAlarmService.pageHistory(query));
    }

    @GetMapping("/delete")
    public PojoResult<Boolean> deleteAlarmConfig(@RequestParam("hostId") String hostId) {
        return PojoResult.succeed(hostAlarmConfigService.deleteAlarmConfigByHostId(hostId));
    }
}
