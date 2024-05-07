package com.rany.cake.devops.base.service.tail;

import com.rany.cake.devops.base.service.tail.impl.ExecTailFileHandler;
import com.rany.cake.devops.base.service.tail.impl.TrackerTailFileHandler;
import com.rany.cake.devops.base.util.tail.FileTailMode;
import com.rany.cake.toolkit.lang.function.select.Branches;
import com.rany.cake.toolkit.lang.function.select.Selector;
import com.rany.cake.toolkit.lang.io.SafeCloseable;
import org.springframework.web.socket.WebSocketSession;

/**
 * tail 接口
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/18 17:05
 */
public interface ITailHandler extends SafeCloseable {

    /**
     * 开始
     *
     * @throws Exception Exception
     */
    void start() throws Exception;

    /**
     * 获取机器id
     *
     * @return 机器id
     */
    String getMachineId();

    /**
     * 获取文件路径
     *
     * @return 文件路径
     */
    String getFilePath();

    /**
     * 设置最后修改时间
     */
    default void setLastModify() {
    }

    /**
     * 写入命令
     *
     * @param command command
     */
    default void write(String command) {
    }

    /**
     * 获取实际执行 handler
     *
     * @param mode    mode
     * @param hint    hint
     * @param session session
     * @return handler
     */
    static ITailHandler with(FileTailMode mode, TailFileHint hint, WebSocketSession session) {
        return Selector.<FileTailMode, ITailHandler>of(mode)
                .test(Branches.eq(FileTailMode.TRACKER)
                        .then(() -> new TrackerTailFileHandler(hint, session)))
                .test(Branches.eq(FileTailMode.TAIL)
                        .then(() -> new ExecTailFileHandler(hint, session)))
                .get();
    }

}
