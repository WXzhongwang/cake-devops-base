package com.rany.cake.devops.base.api.service;

import com.rany.cake.devops.base.api.command.system.ConfigIpListCommand;
import com.rany.cake.devops.base.api.dto.system.*;
import com.rany.cake.devops.base.util.system.SystemCleanType;
import com.rany.cake.devops.base.util.system.SystemEnvAttr;

import java.util.List;

/**
 * @author zhongshengwang
 * @version 1.0
 * @date 2025/4/15 12:12
 * @slogon 找到银弹
 */
public interface SystemService {

    /**
     * 获取 ip 配置
     *
     * @param ip ip
     * @return ip 信息
     */
    IpListConfigDTO getIpInfo(String ip);

    /**
     * 配置 ip 过滤器列表
     *
     * @param command request
     */
    void configIpFilterList(ConfigIpListCommand command);

    /**
     * 清理系统文件
     *
     * @param cleanType 文件类型
     */
    void cleanSystemFile(SystemCleanType cleanType);


    /**
     * 分析磁盘占用空间
     */
    SystemSpaceAnalysisDTO analysisSystemSpace();

    /**
     * 获取系统分析信息
     *
     * @return 系统分析信息
     */
    SystemAnalysisDTO getSystemAnalysis();


    /**
     * 获取系统配置项
     *
     * @return 配置项
     */
    SystemOptionDTO getSystemOptions();

    /**
     * 更新系统配置
     *
     * @param env   env
     * @param value value
     */
    void updateSystemOption(SystemEnvAttr env, String value);

    /**
     * 获取线程池指标
     *
     * @return 指标
     */
    List<ThreadPoolMetricsDTO> getThreadPoolMetrics();
}
