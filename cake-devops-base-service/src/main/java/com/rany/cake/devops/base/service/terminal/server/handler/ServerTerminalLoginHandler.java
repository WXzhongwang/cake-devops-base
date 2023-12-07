package com.rany.cake.devops.base.service.terminal.server.handler;

import com.google.gson.GsonBuilder;
import com.rany.cake.devops.base.domain.aggregate.TerminalSession;
import com.rany.cake.devops.base.service.terminal.AbstractServerTerminalHandler;
import com.rany.cake.devops.base.service.terminal.enums.MessageState;
import com.rany.cake.devops.base.service.terminal.server.message.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

@Slf4j
@Component
public class ServerTerminalLoginHandler extends AbstractServerTerminalHandler<Login> {
    @Override
    public Login toMessage(String message) {
        return new GsonBuilder().create().fromJson(message, Login.class);
    }

    @Override
    public void handle(String message, Session session, TerminalSession terminalSession) {
        Login loginMessage = toMessage(message);
    }

    @Override
    public String getState() {
        return MessageState.LOGIN.getState();
    }

}
