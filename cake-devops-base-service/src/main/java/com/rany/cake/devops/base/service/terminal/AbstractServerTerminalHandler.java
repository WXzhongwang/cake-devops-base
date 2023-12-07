package com.rany.cake.devops.base.service.terminal;

import com.rany.cake.devops.base.domain.repository.HostRepository;
import com.rany.cake.devops.base.domain.repository.TerminalSessionInstanceRepository;
import com.rany.cake.devops.base.domain.repository.TerminalSessionRepository;
import com.rany.cake.devops.base.service.terminal.server.message.BaseServerMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;

/**
 * 服务器终端消息处理
 * handler
 *
 * @param <T>
 */
@Slf4j
public abstract class AbstractServerTerminalHandler<T extends BaseServerMessage> implements ITerminalMessageHandler, InitializingBean {
    @Resource
    protected TerminalSessionRepository terminalSessionRepository;
    @Resource
    protected TerminalSessionInstanceRepository terminalSessionInstanceRepository;
    @Resource
    protected HostRepository hostRepository;

    abstract protected T toMessage(String message);

    protected void heartbeat(String sessionId) {
    }

    @Override
    public void afterPropertiesSet() {
        ServerTerminalMessageHandlerFactory.register(this);
    }
}
