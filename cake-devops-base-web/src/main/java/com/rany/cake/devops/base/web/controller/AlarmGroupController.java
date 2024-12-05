package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.alarm.CreateAlarmGroupCommand;
import com.rany.cake.devops.base.api.command.alarm.DeleteAlarmGroupCommand;
import com.rany.cake.devops.base.api.command.alarm.ModifyAlarmGroupCommand;
import com.rany.cake.devops.base.api.common.enums.EventType;
import com.rany.cake.devops.base.api.dto.AlarmGroupDTO;
import com.rany.cake.devops.base.api.query.alarm.AlarmGroupBasicQuery;
import com.rany.cake.devops.base.api.query.alarm.AlarmGroupPageQuery;
import com.rany.cake.devops.base.api.service.AlarmGroupService;
import com.rany.cake.devops.base.service.aspect.annotation.EventLog;
import com.rany.cake.devops.base.service.base.EventParamsHolder;
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
    @EventLog(EventType.ADD_ALARM_GROUP)
    public PojoResult<String> create(@RequestBody CreateAlarmGroupCommand command) {
        EventParamsHolder.addParam("name", command.getGroupName());
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
    @EventLog(EventType.UPDATE_ALARM_GROUP)
    public PojoResult<Boolean> update(@RequestBody ModifyAlarmGroupCommand command) {
        AlarmGroupBasicQuery basicQuery = new AlarmGroupBasicQuery();
        basicQuery.setAlarmGroupId(command.getAlarmGroupId());
        AlarmGroupDTO alarmGroup = alarmGroupService.getAlarmGroup(basicQuery);
        EventParamsHolder.addParam("name", alarmGroup.getGroupName());
        return PojoResult.succeed(alarmGroupService.modifyAlarmGroup(command));
    }

    @PostMapping("/delete")
    @EventLog(EventType.DELETE_ALARM_GROUP)
    public PojoResult<Boolean> delete(@RequestBody DeleteAlarmGroupCommand command) {
        AlarmGroupBasicQuery basicQuery = new AlarmGroupBasicQuery();
        basicQuery.setAlarmGroupId(command.getAlarmGroupId());
        AlarmGroupDTO alarmGroup = alarmGroupService.getAlarmGroup(basicQuery);
        EventParamsHolder.addParam("name", alarmGroup.getGroupName());
        return PojoResult.succeed(alarmGroupService.deleteAlarmGroup(command));
    }

    @PostMapping("/page")
    public PageResult<AlarmGroupDTO> page(@RequestBody AlarmGroupPageQuery pageQuery) {
        return PageResult.succeed(alarmGroupService.pageAlarmGroup(pageQuery));
    }
}
