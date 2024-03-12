package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.host.alarm.SetHostAlarmConfigCommand;
import com.rany.cake.devops.base.api.command.host.alarm.SetHostAlarmGroupCommand;
import com.rany.cake.devops.base.api.dto.HostAlarmConfigDTO;
import com.rany.cake.devops.base.api.dto.HostAlarmConfigWrapperDTO;

/**
 * 主机监控设置服务
 *
 * @author zhongshengwang
 * @description 主机服务
 * @date 2023/1/15 17:33
 * @email 18668485565163.com
 */
public interface HostAlarmConfigService {

    /**
     * 创建主机监控配置
     *
     * @param command 创建
     * @return result
     */
    PojoResult<Boolean> setHostAlarmConfig(SetHostAlarmConfigCommand command);

    /**
     * 获取主机告警配置
     *
     * @param hostId
     * @return
     */
    PojoResult<HostAlarmConfigWrapperDTO> getHostAlarmConfig(String hostId);

    /**
     * 创建主机告警组配置
     *
     * @param command 创建
     * @return result
     */
    PojoResult<Boolean> setHostAlarmGroup(SetHostAlarmGroupCommand command);

    /**
     * 根据主机查看告警配置列表
     *
     * @param hostId 主机
     * @return 查看主机告警配置列表
     */
    ListResult<HostAlarmConfigDTO> selectAlarmConfigByHostId(String hostId);

    /**
     * 删除主机查看告警配置列表
     *
     * @param hostId 主机
     * @return 删除主机告警配置列表
     */
    PojoResult<Boolean> deleteAlarmConfigByHostId(String hostId);
}
