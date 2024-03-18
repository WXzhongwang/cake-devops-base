package com.rany.cake.devops.base.service.handler.agent.request;

import com.rany.cake.devops.base.api.dto.HostAlarmConfigDTO;
import lombok.Data;

import java.util.List;

/**
 * 机器监控同步请求
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/9/1 14:49
 */
@Data
public class MachineMonitorSyncRequest {

    /**
     * 机器id
     */
    private String hostId;

    /**
     * 报警配置
     */
    private List<HostAlarmConfigDTO> alarmConfig;

}
