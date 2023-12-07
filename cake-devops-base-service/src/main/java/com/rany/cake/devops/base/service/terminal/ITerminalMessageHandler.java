package com.rany.cake.devops.base.service.terminal;


import com.rany.cake.devops.base.domain.aggregate.TerminalSession;

import javax.websocket.Session;

public interface ITerminalMessageHandler {

    /**
     * 处理消息
     *
     * @param message         消息
     * @param session         会话
     * @param terminalSession 终端会话
     */
    void handle(String message, Session session, TerminalSession terminalSession);

    String getState();

}
