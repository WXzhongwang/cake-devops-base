package com.rany.cake.devops.plugin.metrics.reduce;


import com.rany.cake.devops.plugin.entity.bo.BaseRangeBO;

/**
 * 时级数据规约器接口
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/7/2 16:09
 */
public interface IMetricsHourReduceResolver<T extends BaseRangeBO> {

    /**
     * 规约数据
     *
     * @param data data
     */
    void reduce(T data);

}
