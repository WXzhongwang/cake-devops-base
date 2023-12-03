package com.rany.cake.devops.base.web.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class WebSocketService {

    @Resource
    private WebSocketServer webSocketServer;

    public void sendMessage(String taskId, String logMessage) {
        try {
            ConcurrentHashMap<String, WebSocketServer> map = webSocketServer.getWebSocketMap();
            WebSocketServer server = map.get(taskId);
            if (server != null) {
                server.sendMessage(logMessage);
            } else {
                log.warn("客户端已退出");
            }
        } catch (IOException e) {
            log.error("向客户端发送消息时出现异常，异常原因:{}", e.getMessage(), e);
        }
    }
}
