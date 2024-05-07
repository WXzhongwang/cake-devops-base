package com.rany.cake.devops.base.service.ws.manager;

/**
 * 管理handler
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/4/19 23:21
 */
public interface TerminalManagementHandler {

    /**
     * 管理员强制下线
     *
     * @throws Exception Exception
     */
    void forcedOffline() throws Exception;

    /**
     * 心跳结束下线
     *
     * @throws Exception Exception
     */
    void heartbeatDownClose() throws Exception;

    /**
     * 主动发送心跳
     */
    void sendHeartbeat();

}
