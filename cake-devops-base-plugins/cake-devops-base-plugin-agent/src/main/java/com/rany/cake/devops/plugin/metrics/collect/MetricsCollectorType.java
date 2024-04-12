package com.rany.cake.devops.plugin.metrics.collect;

import com.rany.cake.devops.plugin.SpringHolder;
import com.rany.cake.devops.plugin.entity.bo.BaseRangeBO;
import lombok.AllArgsConstructor;

/**
 * 数据指标采集类型
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/7/4 11:28
 */
@AllArgsConstructor
public enum MetricsCollectorType {

    /**
     * 处理器
     */
    CPU(CpuMetricsCollector.class),

    /**
     * 内存
     */
    MEMORY(MemoryMetricsCollector.class),

    /**
     * 网络带宽
     */
    NET(NetBandwidthCollector.class),

    /**
     * 硬盘
     */
    DISK(DiskMetricsCollector.class),

    ;

    private final Class<? extends IMetricsCollector<?>> beanClass;

    /**
     * 获取采集器 bean
     */
    @SuppressWarnings("unchecked")
    public <T extends IMetricsCollector<? extends BaseRangeBO>> T getCollectBean() {
        return (T) SpringHolder.getBean(beanClass);
    }

}
