package com.rany.cake.devops.plugin.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置常量
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/1 14:48
 */
@Component
public class PropertiesConst {

    /**
     * 机器id
     */
    public static String HOST_ID;

    /**
     * 版本
     */
    public static String AGENT_VERSION;

    @Value("${machineId:}")
    private void setHostId(String hostId) {
        PropertiesConst.HOST_ID = hostId;
    }

    @Value("${agent.version}")
    private void setAgentVersion(String agentVersion) {
        PropertiesConst.AGENT_VERSION = agentVersion;
    }

}
