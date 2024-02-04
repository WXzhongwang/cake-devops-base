package com.rany.cake.devops.base.api.command.alarm;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 告警组
 *
 * @author zhongshengwang
 * @description 告警组
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ModifyAlarmGroupCommand extends BaseCommand {

    private Long alarmGroupId;

    /**
     * 描述
     */
    private String groupName;
    /**
     * 描述
     */
    private String groupDescription;

    /**
     * 通知人
     */
    private List<String> accountIds;


    /**
     * 停止方式ID
     */
    private List<Long> notifyIdList;
}
