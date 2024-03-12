package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.host.alarm.SetHostAlarmConfigCommand;
import com.rany.cake.devops.base.api.command.host.alarm.SetHostAlarmGroupCommand;
import com.rany.cake.devops.base.api.dto.HostAlarmConfigDTO;
import com.rany.cake.devops.base.api.dto.HostAlarmConfigWrapperDTO;
import com.rany.cake.devops.base.api.service.HostAlarmConfigService;
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


    @GetMapping("/get-config")
    public PojoResult<HostAlarmConfigWrapperDTO> getAlarmConfig(@RequestParam("hostId") String hostId) {
        return hostAlarmConfigService.getHostAlarmConfig(hostId);
    }

    @PostMapping("/set-alarm-config")
    public PojoResult<Boolean> setAlarmConfig(SetHostAlarmConfigCommand command) {
        return hostAlarmConfigService.setHostAlarmConfig(command);
    }

    @PostMapping("/set-alarm-group")
    public PojoResult<Boolean> setAlarmHost(SetHostAlarmGroupCommand command) {
        return hostAlarmConfigService.setHostAlarmGroup(command);
    }

    @GetMapping("/get-config-list")
    public ListResult<HostAlarmConfigDTO> getAlarmConfigList(@RequestParam("hostId") String hostId) {
        return hostAlarmConfigService.selectAlarmConfigByHostId(hostId);
    }

    @GetMapping("/delete")
    public PojoResult<Boolean> deleteAlarmConfig(@RequestParam("hostId") String hostId) {
        return hostAlarmConfigService.deleteAlarmConfigByHostId(hostId);
    }
}
