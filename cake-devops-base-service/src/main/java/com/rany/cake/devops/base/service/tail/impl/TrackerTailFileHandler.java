package com.rany.cake.devops.base.service.tail.impl;

import com.alibaba.fastjson.JSON;
import com.rany.cake.devops.base.service.base.WebSockets;
import com.rany.cake.devops.base.service.tail.ITailHandler;
import com.rany.cake.devops.base.service.tail.TailFileHint;
import com.rany.cake.devops.base.util.SchedulerPools;
import com.rany.cake.devops.base.util.ws.WsCloseCode;
import com.rany.cake.toolkit.ext.tail.Tracker;
import com.rany.cake.toolkit.ext.tail.delay.DelayTrackerListener;
import com.rany.cake.toolkit.ext.tail.handler.DataHandler;
import com.rany.cake.toolkit.ext.tail.mode.FileNotFoundMode;
import com.rany.cake.toolkit.ext.tail.mode.FileOffsetMode;
import com.rany.cake.toolkit.lang.Threads;
import com.rany.cake.toolkit.lang.thread.HookRunnable;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * tracker 方式
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/18 17:36
 */
@Slf4j
public class TrackerTailFileHandler implements ITailHandler, DataHandler {

    @Getter
    private final String token;

    /**
     * session
     */
    private final WebSocketSession session;

    /**
     * hint
     */
    private final TailFileHint hint;

    private DelayTrackerListener tracker;

    private volatile boolean close;

    @Getter
    private final String filePath;

    public TrackerTailFileHandler(TailFileHint hint, WebSocketSession session) {
        this.token = hint.getToken();
        this.hint = hint;
        this.filePath = hint.getPath();
        this.session = session;
        log.info("tail TRACKER 监听文件初始化 token: {}, hint: {}", token, JSON.toJSONString(hint));
    }

    @Override
    public void start() {
        this.tracker = new DelayTrackerListener(filePath, this);
        tracker.delayMillis(hint.getDelay());
        tracker.charset(hint.getCharset());
        tracker.offset(FileOffsetMode.LINE, hint.getOffset());
        tracker.notFoundMode(FileNotFoundMode.WAIT_COUNT, 10);
        Threads.start(new HookRunnable(() -> {
            log.info("tail TRACKER 开始监听文件 token: {}", token);
            tracker.tail();
        }, this::callback), SchedulerPools.TAIL_SCHEDULER);
    }

    @Override
    public void setLastModify() {
        tracker.setFileLastModifyTime();
    }

    @Override
    public String getMachineId() {
        return "1";
    }

    /**
     * 回调
     */
    @SneakyThrows
    private void callback() {
        log.info("tail TRACKER 监听文件结束 token: {}", token);
        WebSockets.close(session, WsCloseCode.EOF);
    }

    @SneakyThrows
    @Override
    public void read(byte[] bytes, int len, Tracker tracker) {
        if (session.isOpen()) {
            session.sendMessage(new BinaryMessage(bytes, 0, len, true));
        }
    }

    @Override
    @SneakyThrows
    public void close() {
        if (close) {
            return;
        }
        this.close = true;
        if (tracker != null) {
            tracker.stop();
        }
    }

    @Override
    public String toString() {
        return filePath;
    }

}
