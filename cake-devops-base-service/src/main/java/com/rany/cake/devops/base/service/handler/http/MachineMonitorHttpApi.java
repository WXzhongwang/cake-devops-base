package com.rany.cake.devops.base.service.handler.http;

import com.rany.cake.toolkit.http.support.HttpMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 机器监控插件 http api
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/1 10:55
 */
@Getter
@AllArgsConstructor
public enum MachineMonitorHttpApi implements HttpApiDefined {

    /**
     * 端点 ping
     */
    ENDPOINT_PING("/cake/machine-monitor-agent/api/endpoint/ping", HttpMethod.GET),

    /**
     * 端点 version
     */
    ENDPOINT_VERSION("/cake/machine-monitor-agent/api/endpoint/version", HttpMethod.GET),

    /**
     * 端点 sync
     */
    ENDPOINT_SYNC("/cake/machine-monitor-agent/api/endpoint/sync", HttpMethod.POST),

    /**
     * 指标 获取机器基本指标
     */
    METRICS_BASE("/cake/machine-monitor-agent/api/metrics/base", HttpMethod.GET),

    /**
     * 指标 获取系统负载
     */
    METRICS_SYSTEM_LOAD("/cake/machine-monitor-agent/api/metrics/system-load", HttpMethod.GET),

    /**
     * 指标 获取硬盘名称
     */
    METRICS_DISK_NAME("/cake/machine-monitor-agent/api/metrics/disk-name", HttpMethod.GET),

    /**
     * 指标 获取 top 进程
     */
    METRICS_TOP_PROCESSES("/cake/machine-monitor-agent/api/metrics/top-processes", HttpMethod.GET),

    /**
     * 监控 获取cpu数据
     */
    MONITOR_CPU("/cake/machine-monitor-agent/api/monitor-statistic/cpu", HttpMethod.POST),

    /**
     * 监控 获取内存数据
     */
    MONITOR_MEMORY("/cake/machine-monitor-agent/api/monitor-statistic/memory", HttpMethod.POST),

    /**
     * 监控 获取网络数据
     */
    MONITOR_NET("/cake/machine-monitor-agent/api/monitor-statistic/net", HttpMethod.POST),

    /**
     * 监控 获取磁盘数据
     */
    MONITOR_DISK("/cake/machine-monitor-agent/api/monitor-statistic/disk", HttpMethod.POST),

    ;

    /**
     * 请求路径
     */
    private final String path;

    /**
     * 请求方法
     */
    private final HttpMethod method;

}
