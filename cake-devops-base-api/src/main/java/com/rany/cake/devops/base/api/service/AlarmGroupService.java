package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.alarm.CreateAlarmGroupCommand;
import com.rany.cake.devops.base.api.command.alarm.DeleteAlarmGroupCommand;
import com.rany.cake.devops.base.api.command.alarm.ModifyAlarmGroupCommand;
import com.rany.cake.devops.base.api.dto.AlarmGroupDTO;
import com.rany.cake.devops.base.api.query.AlarmGroupBasicQuery;
import com.rany.cake.devops.base.api.query.AlarmGroupPageQuery;

/**
 * 告警组
 *
 * @author zhongshengwang
 * @description 告警组
 * @date 2023/1/15 17:33
 * @email 18668485565163.com
 */
public interface AlarmGroupService {

    /**
     * 创建
     *
     * @param command 创建
     * @return webhookId
     */
    PojoResult<String> createAlarmGroup(CreateAlarmGroupCommand command);

    /**
     * 更新
     *
     * @param command 更新
     * @return success
     */
    PojoResult<Boolean> modifyAlarmGroup(ModifyAlarmGroupCommand command);

    /**
     * 删除
     *
     * @param command 删除
     * @return success
     */
    PojoResult<Boolean> deleteAlarmGroup(DeleteAlarmGroupCommand command);

    /**
     * 获取详情
     *
     * @param basicQuery 详情
     * @return 详情
     */
    PojoResult<AlarmGroupDTO> getAlarmGroup(AlarmGroupBasicQuery basicQuery);


    /**
     * 分页查询列表
     *
     * @param pageQuery 查询
     * @return 列表
     */
    PageResult<AlarmGroupDTO> pageAlarmGroup(AlarmGroupPageQuery pageQuery);
}
