package com.rany.cake.devops.base.service.ws.watcher;


import com.rany.cake.devops.base.api.dto.user.UserDTO;
import com.rany.cake.devops.base.service.base.WebSockets;
import com.rany.cake.devops.base.service.ws.IOperateHandler;
import com.rany.cake.devops.base.service.ws.PassportService;
import com.rany.cake.devops.base.service.ws.manager.TerminalSessionManager;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.util.terminal.TerminalClientOperate;
import com.rany.cake.devops.base.util.ws.WsCloseCode;
import com.rany.cake.devops.base.util.ws.WsProtocol;
import com.rany.cake.toolkit.lang.wrapper.Tuple;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import javax.annotation.Resource;

/**
 * terminal watcher 处理器
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/7/29 11:21
 */
@Slf4j
@Component("terminalWatcherHandler")
public class TerminalWatcherHandler implements WebSocketHandler {

    @Resource
    private TerminalSessionManager terminalSessionManager;

    @Resource
    private PassportService passportService;

    @Override
    public void afterConnectionEstablished(@NotNull WebSocketSession session) {
        log.info("terminal-watcher 已建立连接 token: {}, id: {}, params: {}", WebSockets.getToken(session), session.getId(), session.getAttributes());
    }

    @Override
    public void handleMessage(@NotNull WebSocketSession session, @NotNull WebSocketMessage<?> message) {
        if (!(message instanceof TextMessage)) {
            return;
        }
        String token = session.getId();
        try {
            // 解析请求
            Tuple tuple = WebSockets.parsePayload(((TextMessage) message).getPayload());
            if (tuple == null) {
                WebSockets.sendText(session, WsProtocol.ERROR.get());
                return;
            }
            TerminalClientOperate operate = tuple.get(0);
            String body = tuple.get(1);

            // 建立连接
            if (operate == TerminalClientOperate.CONNECT) {
                // 建立连接
                if (session.getAttributes().get(WebSockets.AUTHED) != null) {
                    return;
                }
                this.auth(session, body);
                return;
            }
            if (operate != TerminalClientOperate.KEY && operate != TerminalClientOperate.CLEAR) {
                return;
            }
            // 检查连接
            if (session.getAttributes().get(WebSockets.AUTHED) == null) {
                WebSockets.close(session, WsCloseCode.VALID);
                return;
            }
            // 检查是否只读
            final boolean readonly = Const.ENABLE.equals(session.getAttributes().get(WebSockets.READONLY));
            if (operate == TerminalClientOperate.KEY && readonly) {
                return;
            }
            // 获取连接
            String terminalToken = session.getAttributes().get(WebSockets.TOKEN).toString();
            IOperateHandler handler = terminalSessionManager.getSession(terminalToken);
            if (handler == null) {
                WebSockets.close(session, WsCloseCode.UNKNOWN_CONNECT);
                return;
            }
            // 操作
            handler.handleMessage(operate, body);
        } catch (Exception e) {
            log.error("terminal 处理操作异常 token: {}", token, e);
            WebSockets.close(session, WsCloseCode.RUNTIME_EXCEPTION);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, @NotNull Throwable exception) {
        log.error("terminal-watcher 操作异常拦截 token: {}", session.getId(), exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("terminal-watcher 关闭连接 token: {}, code: {}, reason: {}", session.getId(), status.getCode(), status.getReason());
        // 这时候主连接可能已经关了
        IOperateHandler handler = terminalSessionManager.getSession((String) session.getAttributes().get(WebSockets.TOKEN));
        if (handler == null) {
            return;
        }
        handler.getWatcher().removeWatcher(session.getId());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 认证
     *
     * @param session    session
     * @param loginToken loginToken
     */
    private void auth(WebSocketSession session, String loginToken) {
        // 检查参数
        String userId = (String) session.getAttributes().get(WebSockets.UID);
        // 获取登陆用户
        UserDTO userDTO = passportService.getUserByToken(loginToken, null);
        if (userDTO == null || !userId.equals(userDTO.getUserId())) {
            WebSockets.close(session, WsCloseCode.IDENTITY_MISMATCH);
            return;
        }
        session.getAttributes().put(WebSockets.AUTHED, 1);
        // 获取连接
        String terminalToken = session.getAttributes().get(WebSockets.TOKEN).toString();
        IOperateHandler handler = terminalSessionManager.getSession(terminalToken);
        // 设置 watcher
        handler.getWatcher().addWatcher(session);
        WebSockets.sendText(session, WsProtocol.CONNECTED.get());
    }

}
