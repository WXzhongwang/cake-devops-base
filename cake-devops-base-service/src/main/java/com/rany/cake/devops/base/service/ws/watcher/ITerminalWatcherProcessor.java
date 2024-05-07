package com.rany.cake.devops.base.service.ws.watcher;

import com.rany.cake.toolkit.lang.Watchable;
import com.rany.cake.toolkit.lang.io.SafeCloseable;
import org.springframework.web.socket.WebSocketSession;

/**
 * terminal watcher 处理器接口
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/7/29 11:27
 */
public interface ITerminalWatcherProcessor extends Watchable, SafeCloseable {

    /**
     * 发送消息
     *
     * @param message message
     */
    void sendMessage(byte[] message);

    /**
     * 添加 watcher
     *
     * @param session session
     */
    void addWatcher(WebSocketSession session);

    /**
     * 移除 watcher
     *
     * @param id id
     */
    void removeWatcher(String id);

}
