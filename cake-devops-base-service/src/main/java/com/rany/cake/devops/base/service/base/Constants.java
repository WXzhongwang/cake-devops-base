package com.rany.cake.devops.base.service.base;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/19 17:54
 * @email 18668485565163.com
 */
public final class Constants {


    private Constants() {

    }

    public static final String SYSTEM_NAME = "cake-devops";
    public static final String SYSTEM_VERSION = "1.0.0";
    public final static String TRACE_ID = "traceId";
    /**
     * 默认端口
     */
    public static final Integer DEFAULT_SERVER_PORT = 7100;
    public static final String DEFAULT_NAMESPACE_PREFIX = "cake-";
    public static final String USER_HOME_DIR = "/home/admin";
    /**
     * 打包主机组名称，不存在会创建改组
     */
    public static final String DEFAULT_GROUP_NAME = "package";
    public static final String AUTHORIZED_KEYS = "~/.ssh/authorized_keys";
    public static final String REMOTE_BASE = "/Users/yuanjinxiu/ci/";


    /**
     * 秒时间戳
     */
    public static final long SECOND_TIME = 1000L;

    /**
     * 分钟时间戳
     */
    public static final long MINUTE_TIME = 60 * SECOND_TIME;

    /**
     * 小时时间戳
     */
    public static final long HOUR_TIME = 60 * MINUTE_TIME;

    /**
     * 天时间戳
     */
    public static final long DAY_TIME = 24 * HOUR_TIME;
    /**
     * 超时时间5分钟
     */
    public static final Long WEBSOCKET_TIMEOUT = MINUTE_TIME * 5;
}
