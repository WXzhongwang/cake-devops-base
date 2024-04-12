package com.rany.cake.devops.plugin.common.http;

import com.rany.cake.toolkit.http.support.HttpMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * orion-ops 暴露服务api
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/31 19:09
 */
@Getter
@AllArgsConstructor
public enum OrionOpsExposeHttpApi implements HttpApiDefined {

    /**
     * 获取机器报警配置
     */
    GET_MACHINE_ALARM_CONFIG("/cake/expose-api/machine-alarm/get-config", HttpMethod.GET),

    /**
     * 触发机器报警
     */
    TRIGGER_MACHINE_ALARM("/cake/expose-api/machine-alarm/trigger-alarm", HttpMethod.POST),

    /**
     * 获取机器报警配置
     */
    MACHINE_MONITOR_STARTED("/cake/expose-api/machine-monitor/started", HttpMethod.GET),

    ;

    private final String path;

    private final HttpMethod method;

}
