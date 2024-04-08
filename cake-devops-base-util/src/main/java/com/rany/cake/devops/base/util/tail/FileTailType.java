package com.rany.cake.devops.base.util.tail;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * tail 文件类型
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/10 18:48
 */
@AllArgsConstructor
@Getter
public enum FileTailType {

    /**
     * 命令执行日志
     *
     * @see com.orion.ops.constant.system.SystemEnvAttr#LOG_PATH
     */
    EXEC_LOG(10, true),

    /**
     * tail 列表
     */
    TAIL_LIST(20, false),

    /**
     * 应用构建日志
     *
     * @see com.orion.ops.constant.system.SystemEnvAttr#LOG_PATH
     */
    APP_BUILD_LOG(30, true),

    /**
     * 应用发布日志
     *
     * @see com.orion.ops.constant.system.SystemEnvAttr#LOG_PATH
     */
    APP_RELEASE_LOG(40, true),

    /**
     * 调度任务机器日志
     *
     * @see com.orion.ops.constant.system.SystemEnvAttr#LOG_PATH
     */
    SCHEDULER_TASK_MACHINE_LOG(50, true),

    /**
     * 应用操作日志
     *
     * @see com.orion.ops.constant.system.SystemEnvAttr#LOG_PATH
     */
    APP_ACTION_LOG(60, true),

    ;

    private final Integer type;

    /**
     * 是否为本地文件
     */
    private final boolean isLocal;

    public static FileTailType of(Integer type) {
        if (type == null) {
            return null;
        }
        for (FileTailType value : values()) {
            if (value.type.equals(type)) {
                return value;
            }
        }
        return null;
    }

}
