package com.rany.cake.devops.base.api.command.host.alarm;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;

import java.util.Date;

/**
 * 机器报警请求
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/29 16:59
 */
@Data
@SuppressWarnings("ALL")
public class HostAlarmCommand extends BaseCommand {

    private String hostId;

    /**
     * @see 报警类型 10: cpu使用率 20: 内存使用率
     */
    private Integer type;

    private Double alarmValue;

    private Date alarmTime;
}
