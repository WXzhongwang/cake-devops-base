package com.rany.cake.devops.base.web.websocket;


import com.rany.cake.devops.base.domain.aggregate.TerminalSession;
import com.rany.cake.devops.base.domain.base.HostInfo;
import com.rany.cake.devops.base.domain.enums.SessionTypeEnum;
import com.rany.cake.devops.base.domain.factory.TerminalSessionFactory;
import com.rany.cake.devops.base.domain.repository.TerminalSessionRepository;
import com.rany.cake.devops.base.service.base.Constants;
import com.rany.cake.devops.base.service.terminal.SentOutputTask;
import com.rany.cake.devops.base.service.terminal.ServerTerminalMessageHandlerFactory;
import com.rany.cake.devops.base.service.terminal.enums.MessageState;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.UUID;

/**
 * 终端管理
 *
 * @author zhongshengwang
 */
@Slf4j
@ServerEndpoint(value = "/api/ws/terminal")
@Component
public class ServerTerminalController extends SocketBaseController {

    /**
     * 当前会话UUID
     */
    private final String sessionId = UUID.randomUUID().toString();

    /**
     * 超时时间5分钟
     */
    public static final Long WEBSOCKET_TIMEOUT = Constants.MINUTE_TIME * 5;

    private static final HostInfo SERVER_INFO = HostInfo.build();

    private static TerminalSessionFactory terminalSessionFactory;


    private static TerminalSessionRepository terminalSessionRepository;

    private TerminalSession terminalSession;

    private static ThreadPoolTaskExecutor serverTerminalExecutor;

    @Resource
    public void setThreadPoolTaskExecutor(ThreadPoolTaskExecutor serverTerminalExecutor) {
        ServerTerminalController.serverTerminalExecutor = serverTerminalExecutor;
    }

    @Resource
    public void setTerminalSessionRepository(TerminalSessionRepository terminalSessionRepository) {
        ServerTerminalController.terminalSessionRepository = terminalSessionRepository;
    }

    @Resource
    public void setTerminalSessionFactory(TerminalSessionFactory terminalSessionFactory) {
        ServerTerminalController.terminalSessionFactory = terminalSessionFactory;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        try {
            log.info("Server terminal session try to connect: instanceIP={}, sessionId={}", SERVER_INFO.getHostAddress(), sessionId);
            TerminalSession terminalSession = terminalSessionFactory.build(sessionId, SERVER_INFO, SessionTypeEnum.WEB_TERMINAL);
            terminalSessionRepository.save(terminalSession);
            this.terminalSession = terminalSession;
            session.setMaxIdleTimeout(WEBSOCKET_TIMEOUT);
            // 线程启动
            serverTerminalExecutor.execute(new SentOutputTask(sessionId, session));
        } catch (Exception e) {
            log.error("Server terminal create connection error: {}", e.getMessage(), e);
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        ServerTerminalMessageHandlerFactory.getHandlerByState(MessageState.CLOSE.getState())
                .handle("", session, terminalSession);
    }

    /**
     * 收到客户端消息后调用的方法
     * Session session
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage(maxMessageSize = 20 * 1024)
    public void onMessage(String message, Session session) {
        if (!session.isOpen() || StringUtils.isEmpty(message)) {
            return;
        }
        String state = getState(message);
        ServerTerminalMessageHandlerFactory.getHandlerByState(state).handle(message, session, terminalSession);
    }

    /**
     * 出现错误
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
    }

}
