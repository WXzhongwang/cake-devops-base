package com.rany.cake.devops.base.web.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class WebSocketService {

    @Resource
    private WebSocketServer webSocketServer;

    public void sendMessage(String releaseId, String logMessage) {
        try {
            ConcurrentHashMap<String, List<WebSocketServer>> map = webSocketServer.getWebSocketMap();
            List<WebSocketServer> servers = map.get(releaseId);
            if (servers != null && !servers.isEmpty()) {
                for (WebSocketServer server : servers) {
                    server.sendMessage(logMessage);
                }
            } else {
                log.warn("客户端已退出");
            }
        } catch (IOException e) {
            log.error("向客户端发送消息时出现异常，异常原因:{}", e.getMessage(), e);
        }
    }
}
