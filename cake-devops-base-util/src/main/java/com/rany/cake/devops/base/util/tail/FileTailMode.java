package com.rany.cake.devops.base.util.tail;

import com.rany.cake.devops.base.util.system.SystemEnvAttr;
import com.rany.cake.toolkit.lang.utils.Strings;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 宿主机tail 追踪模式
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2021/6/10 19:10
 */
@AllArgsConstructor
@Getter
public enum FileTailMode {

    /**
     * 仅宿主机
     */
    TRACKER("tracker"),

    /**
     * tail 命令
     * <p>
     * 宿主机 远程机器
     */
    TAIL("tail"),

    ;

    private final String mode;

    public static FileTailMode of(String mode) {
        return of(mode, false);
    }

    public static FileTailMode of(String mode, boolean hostMachine) {
        if (Strings.isBlank(mode)) {
            return hostMachine ? TRACKER : TAIL;
        }
        for (FileTailMode value : values()) {
            if (value.mode.equals(mode)) {
                return value;
            }
        }
        return hostMachine ? TRACKER : TAIL;
    }

    /**
     * 获取宿主机 tailMode
     *
     * @return tailMode
     */
    public static String getHostTailMode() {
        String mode = SystemEnvAttr.TAIL_MODE.getValue();
        return of(mode, true).getMode();
    }

}
