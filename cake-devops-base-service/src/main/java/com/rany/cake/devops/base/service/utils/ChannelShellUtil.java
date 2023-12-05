package com.rany.cake.devops.base.service.utils;

import com.jcraft.jsch.ChannelShell;

public class ChannelShellUtil {

    /**
     * zh_CN.UTF-8  en_US.UTF-8 zh_CN.GB231
     */
    public static final String DEF_UNICODE = "en_US.UTF-8";

    /**
     * 设置默认属性
     *
     * @param channel channel
     */
    public static void setDefault(ChannelShell channel) {
        channel.setEnv("LANG", DEF_UNICODE);
        // channel.setEnv("LC_CTYPE", UNICODE);
        // channel.setEnv("LC_ALL", UNICODE);
        // SSH 代理转发
        channel.setAgentForwarding(false);
        channel.setPtyType("xterm");
    }

}
