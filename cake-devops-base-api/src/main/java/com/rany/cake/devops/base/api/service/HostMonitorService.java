package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.monitor.InstallMonitorAgentCommand;
import com.rany.cake.devops.base.api.command.monitor.SyncMonitorAgentCommand;
import com.rany.cake.devops.base.api.command.monitor.UpdateMonitorAgentCommand;
import com.rany.cake.devops.base.api.dto.HostMonitorDTO;
import com.rany.cake.devops.base.api.query.HostMonitorPageQuery;

/**
 * 主机监控服务
 *
 * @author zhongshengwang
 * @description 告警服务
 * @date 2023/1/15 17:33
 * @email 18668485565163.com
 */
public interface HostMonitorService {


    /**
     * 分页监控列表
     *
     * @param hostMonitorPageQuery query
     * @return 分页监控列表
     */
    PageResult<HostMonitorDTO> pageHostMonitor(HostMonitorPageQuery hostMonitorPageQuery);

    /**
     * 查询主机配置
     *
     * @param hostId 主机ID
     * @return 主机配置
     */
    PojoResult<HostMonitorDTO> findByHostId(String hostId);

    /**
     * 安装
     *
     * @param command command
     * @return 安装
     */
    PojoResult<Boolean> installAgent(InstallMonitorAgentCommand command);

    /**
     * 同步机器插件信息
     *
     * @param command command
     * @return 同步
     */
    PojoResult<String> syncAgent(SyncMonitorAgentCommand command);

    /**
     * 更新监控配置
     *
     * @param command command
     * @return 更新
     */
    PojoResult<Boolean> updateMonitorConfig(UpdateMonitorAgentCommand command);

    /**
     * 获取版本
     *
     * @param url         url
     * @param accessToken accessToken
     * @return version
     */
    PojoResult<String> getMonitorVersion(String url, String accessToken);

    /**
     * 检测监控插件状态
     *
     * @param hostId 主机ID
     * @return 监控信息
     */
    PojoResult<HostMonitorDTO> checkMonitorStatus(String hostId);

}
