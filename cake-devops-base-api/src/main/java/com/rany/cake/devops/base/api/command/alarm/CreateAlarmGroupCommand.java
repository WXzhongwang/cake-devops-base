package com.rany.cake.devops.base.api.command.alarm;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 创建告警组
 *
 * @author zhongshengwang
 * @description 创建告警组
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateAlarmGroupCommand extends BaseCommand {

    private String groupName;

    private String groupDescription;

    private List<String> accountIds;

    /**
     * 通知方式ID
     */
    private List<Long> notifyIdList;
}
