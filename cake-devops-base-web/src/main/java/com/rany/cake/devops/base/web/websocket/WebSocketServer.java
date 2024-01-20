package com.rany.cake.devops.base.web.websocket;

import com.rany.cake.devops.base.service.base.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@ServerEndpoint(value = "/api/ws/devops/{pipeKey}")
@Component
public class WebSocketServer {

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。
     */
    private static final ConcurrentHashMap<String, CopyOnWriteArraySet<WebSocketServer>> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("pipeKey") String pipeKey) {
        try {
            session.setMaxIdleTimeout(Constants.WEBSOCKET_TIMEOUT);
            log.info("current pipeKey:{}", pipeKey);
            this.session = session;
            if (webSocketMap.containsKey(pipeKey)) {
                CopyOnWriteArraySet<WebSocketServer> webSocketServers = webSocketMap.get(pipeKey);
                webSocketServers.add(this);
            } else {
                CopyOnWriteArraySet<WebSocketServer> servers = new CopyOnWriteArraySet<>();
                servers.add(this);
                webSocketMap.put(pipeKey, servers);
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
    public void onClose(@PathParam("pipeKey") String pipeKey) {
        CopyOnWriteArraySet<WebSocketServer> socketServers = webSocketMap.get(pipeKey);
        socketServers.remove(this);  //从set中删除
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

    public ConcurrentHashMap<String, CopyOnWriteArraySet<WebSocketServer>> getWebSocketMap() {
        return webSocketMap;
    }
}
