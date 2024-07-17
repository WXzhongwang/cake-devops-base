package com.rany.cake.devops.base.web.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Component
public class WebSocketService {

    @Resource
    private WebSocketServer webSocketServer;

    public void sendMessage(String pipeKey, String logMessage) {
        try {
            ConcurrentHashMap<String, CopyOnWriteArraySet<WebSocketServer>> map = webSocketServer.getWebSocketMap();
            CopyOnWriteArraySet<WebSocketServer> servers = map.get(pipeKey);
            if (servers != null && !servers.isEmpty()) {
                for (WebSocketServer server : servers) {
                    server.sendMessage(logMessage);
                }
            }
        } catch (IOException e) {
            log.error("向客户端发送消息时出现异常，异常原因:{}", e.getMessage(), e);
        }
    }
}
