package com.rany.cake.devops.base.util;

/**
 * 机器常量
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/4 18:40
 */
public class MachineConst {

    private MachineConst() {
    }

    /**
     * 远程连接尝试次数
     */
    public static final int CONNECT_RETRY_TIMES = 1;

    /**
     * 远程连接超时时间
     */
    public static final int CONNECT_TIMEOUT = Const.MS_S_30;

}
