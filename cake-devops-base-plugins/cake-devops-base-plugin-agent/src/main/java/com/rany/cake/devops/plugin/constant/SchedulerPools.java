package com.rany.cake.devops.plugin.constant;

import com.rany.cake.toolkit.lang.Threads;
import com.rany.cake.toolkit.lang.thread.NamedThreadFactory;
import com.rany.cake.toolkit.lang.utils.SystemUtils;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 调度线程池
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/1 14:38
 */
public class SchedulerPools {

    private SchedulerPools() {
    }

    /**
     * 数据采集调度器
     */
    public static final ScheduledExecutorService COLLECT_SCHEDULER = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("machine-collector-thread-"));

    static {
        SystemUtils.addShutdownHook(() -> {
            Threads.shutdownPoolNow(COLLECT_SCHEDULER, Const.MS_S_3);
        });
    }

}
