package com.rany.cake.devops.base.web.expose;

import com.rany.cake.devops.base.api.command.host.alarm.HostAlarmCommand;
import com.rany.cake.devops.base.api.dto.HostAlarmConfigWrapperDTO;
import com.rany.cake.devops.base.api.service.HostAlarmConfigService;
import com.rany.cake.devops.base.api.service.HostAlarmService;
import com.rany.cake.devops.base.util.Valid;
import com.rany.cake.devops.base.util.enums.MachineAlarmType;
import com.rany.cake.toolkit.lang.wrapper.HttpWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 机器报警 暴露api
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/29 16:32
 */
@Api(tags = "暴露服务-机器报警")
@RestController
@RequestMapping("/cake/expose-api/machine-alarm")
public class HostAlarmExposeController {

    @Resource
    private HostAlarmConfigService machineAlarmConfigService;
    @Resource
    private HostAlarmService hostAlarmService;

    @GetMapping("/get-config")
    @ApiOperation(value = "获取报警配置")
    public HttpWrapper<HostAlarmConfigWrapperDTO> getAlarmConfig(@RequestParam("hostId") String hostId) {
        HostAlarmConfigWrapperDTO hostAlarmConfig = machineAlarmConfigService.getHostAlarmConfig(hostId);
        return HttpWrapper.ok(hostAlarmConfig);
    }

    @PostMapping("/trigger-alarm")
    @ApiOperation(value = "触发机器报警")
    public HttpWrapper<?> triggerMachineAlarm(@RequestBody HostAlarmCommand command) {
        Valid.allNotNull(command.getHostId(), command.getAlarmTime(),
                command.getAlarmValue(), MachineAlarmType.of(command.getType()));
        hostAlarmService.triggerHostAlarm(command);
        return HttpWrapper.ok();
    }

}
