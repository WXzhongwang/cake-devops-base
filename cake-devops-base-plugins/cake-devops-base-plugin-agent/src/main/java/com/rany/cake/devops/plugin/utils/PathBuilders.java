package com.rany.cake.devops.plugin.utils;


import com.rany.cake.toolkit.lang.constant.Const;
import com.rany.cake.toolkit.lang.utils.SystemUtils;

/**
 * 路径构建器
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/7/1 17:22
 */
public class PathBuilders {

    private PathBuilders() {
    }

    public static final String COLLECT_PATH = "/cake-ops/monitor-collect";

    public static final String CPU_DAY_DATA_PATH = "/cpu_day";

    public static final String MEMORY_DAY_DATA_PATH = "/memory_day";

    public static final String NET_DAY_DATA_PATH = "/net_day";

    public static final String DISK_DAY_DATA_PATH = "/disk_day";

    public static final String CPU_MONTH_DATA_PATH = "/cpu_month";

    public static final String MEMORY_MONTH_DATA_PATH = "/memory_month";

    public static final String NET_MONTH_DATA_PATH = "/net_month";

    public static final String DISK_MONTH_DATA_PATH = "/disk_month";

    // -------------------- 天级数据 粒度为配置参数 --------------------

    /**
     * 获取处理器指标数据 天级数据路径
     *
     * @param time 开始时间
     * @return path
     */
    public static String getCpuDayDataPath(String time) {
        return SystemUtils.HOME_DIR
                + COLLECT_PATH
                + CPU_DAY_DATA_PATH
                + "/" + time
                + "." + Const.SUFFIX_LOG;
    }

    /**
     * 获取内存指标数据 天级数据路径
     *
     * @param time 开始时间
     * @return path
     */
    public static String getMemoryDayDataPath(String time) {
        return SystemUtils.HOME_DIR
                + COLLECT_PATH
                + MEMORY_DAY_DATA_PATH
                + "/" + time
                + "." + Const.SUFFIX_LOG;
    }

    /**
     * 获取网卡指标数据 天级数据路径
     *
     * @param time 开始时间
     * @return path
     */
    public static String getNetDayDataPath(String time) {
        return SystemUtils.HOME_DIR
                + COLLECT_PATH
                + NET_DAY_DATA_PATH
                + "/" + time
                + "." + Const.SUFFIX_LOG;
    }

    /**
     * 获取硬盘指标数据 天级数据路径
     *
     * @param time 开始时间
     * @param seq  seq
     * @return path
     */
    public static String getDiskDayDataPath(String time, String seq) {
        return SystemUtils.HOME_DIR
                + COLLECT_PATH
                + DISK_DAY_DATA_PATH
                + "/" + seq
                + "-" + time
                + "." + Const.SUFFIX_LOG;
    }

    // -------------------- 月级数据 粒度为时 --------------------

    /**
     * 获取处理器指标数据路径 月级数据
     *
     * @param time 开始时间
     * @return path
     */
    public static String getCpuMonthDataPath(String time) {
        return SystemUtils.HOME_DIR
                + COLLECT_PATH
                + CPU_MONTH_DATA_PATH
                + "/" + time
                + "." + Const.SUFFIX_LOG;
    }

    /**
     * 获取内存指标数据路径 月级数据
     *
     * @param time 开始时间
     * @return path
     */
    public static String getMemoryMonthDataPath(String time) {
        return SystemUtils.HOME_DIR
                + COLLECT_PATH
                + MEMORY_MONTH_DATA_PATH
                + "/" + time
                + "." + Const.SUFFIX_LOG;
    }

    /**
     * 获取网络带宽指标数据路径 月级数据
     *
     * @param time 开始时间
     * @return path
     */
    public static String getNetMonthDataPath(String time) {
        return SystemUtils.HOME_DIR
                + COLLECT_PATH
                + NET_MONTH_DATA_PATH
                + "/" + time
                + "." + Const.SUFFIX_LOG;
    }

    /**
     * 获取硬盘指标数据路径 月级数据
     *
     * @param time 开始时间
     * @param seq  seq
     * @return path
     */
    public static String getDiskMonthDataPath(String time, String seq) {
        return SystemUtils.HOME_DIR
                + COLLECT_PATH
                + DISK_MONTH_DATA_PATH
                + "/" + seq
                + "-" + time
                + "." + Const.SUFFIX_LOG;
    }

}
