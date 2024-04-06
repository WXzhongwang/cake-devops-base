package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.alarm.CreateAlarmGroupCommand;
import com.rany.cake.devops.base.api.command.alarm.DeleteAlarmGroupCommand;
import com.rany.cake.devops.base.api.command.alarm.ModifyAlarmGroupCommand;
import com.rany.cake.devops.base.api.dto.AlarmGroupDTO;
import com.rany.cake.devops.base.api.query.AlarmGroupBasicQuery;
import com.rany.cake.devops.base.api.query.AlarmGroupPageQuery;
import com.rany.cake.devops.base.api.service.AlarmGroupService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 告警组
 *
 * @author zhongshengwang
 */
@RestController
@RequestMapping("/api/devops/alarm-group")
public class AlarmGroupController {

    @Resource
    private AlarmGroupService alarmGroupService;

    @PostMapping("/create")
    public PojoResult<String> create(@RequestBody CreateAlarmGroupCommand command) {
        return PojoResult.succeed(alarmGroupService.createAlarmGroup(command));
    }

    @GetMapping("/get")
    public PojoResult<AlarmGroupDTO> get(@RequestParam("id") Long alarmGroupId) {
        AlarmGroupBasicQuery alarmGroupBasicQuery = new AlarmGroupBasicQuery();
        alarmGroupBasicQuery.setAlarmGroupId(alarmGroupId);
        AlarmGroupDTO alarmGroup = alarmGroupService.getAlarmGroup(alarmGroupBasicQuery);
        return PojoResult.succeed(alarmGroup);
    }

    @PostMapping("/update")
    public PojoResult<Boolean> update(@RequestBody ModifyAlarmGroupCommand command) {
        return PojoResult.succeed(alarmGroupService.modifyAlarmGroup(command));
    }

    @PostMapping("/delete")
    public PojoResult<Boolean> delete(@RequestBody DeleteAlarmGroupCommand command) {
        return PojoResult.succeed(alarmGroupService.deleteAlarmGroup(command));
    }

    @PostMapping("/page")
    public PageResult<AlarmGroupDTO> page(@RequestBody AlarmGroupPageQuery pageQuery) {
        return PageResult.succeed(alarmGroupService.pageAlarmGroup(pageQuery));
    }
}
