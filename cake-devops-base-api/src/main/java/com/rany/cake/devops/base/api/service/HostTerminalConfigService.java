package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.terminal.SaveTerminalLogCommand;
import com.rany.cake.devops.base.api.command.terminal.UpdateTerminalLogCommand;
import com.rany.cake.devops.base.api.command.terminal.UpdateTerminalSettingCommand;
import com.rany.cake.devops.base.api.dto.HostTerminalConfigDTO;
import com.rany.cake.devops.base.api.dto.HostTerminalLogDTO;
import com.rany.cake.devops.base.api.query.terminal.TerminalAccessDTO;
import com.rany.cake.devops.base.api.query.terminal.TerminalAccessLogPageQuery;

import java.util.List;

/**
 * 终端配置服务
 */
public interface HostTerminalConfigService {

    /**
     * 获取访问配置
     *
     * @param hostId 机器id
     * @param userId 用户
     * @return 访问配置
     */
    TerminalAccessDTO getAccessConfig(String hostId, String userId, String ip);

    /**
     * 获取终端配置
     *
     * @param hostId 机器id
     * @return 配置
     */
    HostTerminalConfigDTO getHostTerminalConfig(String hostId);

    /**
     * 设置终端配置
     *
     * @param command command
     * @return effect
     */
    void updateSetting(UpdateTerminalSettingCommand command);

    /**
     * 添加日志
     *
     * @param entity entity
     * @return id
     */
    Long addTerminalLog(SaveTerminalLogCommand entity);

    /**
     * 更新日志
     *
     * @param entity entity
     * @return effect
     */
    Integer updateAccessLog(UpdateTerminalLogCommand entity);

    /**
     * 查询终端访问日志
     *
     * @param request request
     * @return dataGrid
     */
    Page<HostTerminalLogDTO> listAccessLog(TerminalAccessLogPageQuery request);

    /**
     * 删除终端日志
     *
     * @param idList idList
     * @return effect
     */
    Integer deleteTerminalLog(List<Long> idList);

    /**
     * 通过机器id删除终端配置
     *
     * @param hostIdList 机器id
     * @return effect
     */
    Integer deleteTerminalByMachineIdList(List<String> hostIdList);

    /**
     * 获取下载 terminal 录屏路径
     *
     * @param id id
     * @return path
     */
    String getTerminalScreenFilePath(Long id);
}
