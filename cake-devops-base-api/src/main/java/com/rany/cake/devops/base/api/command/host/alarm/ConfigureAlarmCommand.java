package com.rany.cake.devops.base.api.command.host.alarm;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 设置主机告警监控配置
 *
 * @author zhongshengwang
 * @description 设置主机告警监控配置
 * @date 2022/12/27 20:40
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ConfigureAlarmCommand extends BaseCommand {
    private String hostId;
    private SetHostAlarmConfigCommand cpu;
    private SetHostAlarmConfigCommand memory;
    private List<Long> groupIdList;
}
