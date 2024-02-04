package com.rany.cake.devops.base.api.command.alarm;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 删除
 *
 * @author zhongshengwang
 * @description 删除
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeleteAlarmGroupCommand extends BaseCommand {

    private Long alarmGroupId;

}
