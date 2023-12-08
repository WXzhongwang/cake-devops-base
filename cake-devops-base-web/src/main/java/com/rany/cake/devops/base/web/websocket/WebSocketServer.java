package com.rany.cake.devops.base.web.websocket;

import com.rany.cake.devops.base.service.base.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@ServerEndpoint(value = "/api/ws/cake-devops/{releaseId}")
@Component
public class WebSocketServer {

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。
     */
    private static final ConcurrentHashMap<String, List<WebSocketServer>> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("releaseId") String releaseId) {
        try {
            session.setMaxIdleTimeout(Constants.WEBSOCKET_TIMEOUT);
            log.info("current releaseId:{}", releaseId);
            this.session = session;
            if (webSocketMap.containsKey(releaseId)) {
                List<WebSocketServer> webSocketServers = webSocketMap.get(releaseId);
                webSocketServers.add(this);
            } else {
                List<WebSocketServer> servers = new ArrayList<>();
                servers.add(this);
                webSocketMap.put(releaseId, servers);
            }
            sendMessage("socket连接成功");
        } catch (Exception e) {
            log.error("Create connection error: {}", e.getMessage());
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
    }

    /**
     * 收到客户端消息后调用的方法
     * Session session
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage(maxMessageSize = 1024)
    public void onMessage(String message, Session session) {
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 出现错误
     *
     * @param session session
     * @param error   错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
    }

    public ConcurrentHashMap<String, List<WebSocketServer>> getWebSocketMap() {
        return webSocketMap;
    }
}
