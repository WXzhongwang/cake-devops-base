package com.rany.cake.devops.base.service.tail.impl;

import com.alibaba.fastjson.JSON;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.service.HostDomainService;
import com.rany.cake.devops.base.service.SpringHolder;
import com.rany.cake.devops.base.service.base.WebSockets;
import com.rany.cake.devops.base.service.handler.host.HostConnectionService;
import com.rany.cake.devops.base.service.tail.ITailHandler;
import com.rany.cake.devops.base.service.tail.TailFileHint;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.util.SchedulerPools;
import com.rany.cake.devops.base.util.ws.WsCloseCode;
import com.rany.cake.toolkit.lang.io.Streams;
import com.rany.cake.toolkit.lang.utils.Strings;
import com.rany.cake.toolkit.net.remote.channel.CommandExecutor;
import com.rany.cake.toolkit.net.remote.channel.SessionStore;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.InputStream;

/**
 * tail -f 命令
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/20 18:44
 */
@Slf4j
public class ExecTailFileHandler implements ITailHandler {

    protected static HostDomainService hostDomainService = SpringHolder.getBean(HostDomainService.class);
    protected static HostConnectionService hostConnectionService = SpringHolder.getBean(HostConnectionService.class);

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

    private SessionStore sessionStore;

    private CommandExecutor executor;

    private volatile boolean close;

    public ExecTailFileHandler(TailFileHint hint, WebSocketSession session) {
        this.token = hint.getToken();
        this.hint = hint;
        this.session = session;
        log.info("tail EXEC_TAIL 监听文件初始化 token: {}, hint: {}", token, JSON.toJSONString(hint));
    }

    @Override
    public void start() throws Exception {
        try {
            // 打开session
            Host host = hostDomainService.getHost(new HostId(hint.getMachineId()));
            this.sessionStore = hostConnectionService.openSessionStore(host);
            log.info("tail 建立连接成功 machineId: {}", hint.getMachineId());
        } catch (Exception e) {
            WebSockets.openSessionStoreThrowClose(session, e);
            log.error("tail 建立连接失败-连接远程服务器失败 e: {}, machineId: {}", e, hint.getMachineId());
            return;
        }
        // 打开 command
        this.executor = sessionStore.getCommandExecutor(Strings.replaceCRLF(hint.getCommand()));
        executor.inherit();
        executor.scheduler(SchedulerPools.TAIL_SCHEDULER);
        executor.callback(this::callback);
        executor.streamHandler(this::handler);
        executor.connect();
        executor.exec();
        log.info("tail EXEC_TAIL 监听文件开始 token: {}", token);
    }

    @Override
    public void write(String command) {
        executor.write(command);
    }

    @Override
    public String getMachineId() {
        return hint.getMachineId();
    }

    @Override
    public String getFilePath() {
        return hint.getPath();
    }

    /**
     * 回调
     */
    private void callback() {
        log.info("tail EXEC_TAIL 监听文件结束 token: {}", token);
        WebSockets.close(session, WsCloseCode.EOF);
    }

    /**
     * 处理标准输入流
     *
     * @param input 流
     */
    @SneakyThrows
    private void handler(InputStream input) {
        byte[] buffer = new byte[Const.BUFFER_KB_8];
        int read;
        while ((read = input.read(buffer)) != -1) {
            if (session.isOpen()) {
                session.sendMessage(new BinaryMessage(buffer, 0, read, true));
            }
        }
    }

    @Override
    @SneakyThrows
    public void close() {
        if (close) {
            return;
        }
        this.close = true;
        Streams.close(executor);
        Streams.close(sessionStore);
    }

    @Override
    public String toString() {
        return hint.getPath();
    }

}
