package com.rany.cake.devops.base.service.handler.alarm.push;

import com.rany.cake.devops.base.service.handler.alarm.MachineAlarmContext;

/**
 * 报警推送器
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/29 19:00
 */
public interface IAlarmPusher {

    /**
     * 执行推送
     */
    void push(MachineAlarmContext context);
}
