package com.rany.cake.devops.base.web.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

@Component
public class TestMessageHandler implements WebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Connection established: " + session.getId());
        // Debug point here
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println("Message received: " + message.getPayload());
        // Debug point here
        session.sendMessage(new TextMessage("Message processed"));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("Transport error: " + exception.getMessage());
        // Debug point here
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("Connection closed: " + session.getId());
        // Debug point here
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}