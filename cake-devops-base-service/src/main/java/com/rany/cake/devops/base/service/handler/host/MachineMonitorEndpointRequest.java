package com.rany.cake.devops.base.service.handler.host;

import lombok.Data;

import java.io.Serializable;

@Data
public class MachineMonitorEndpointRequest implements Serializable {

    /**
     * 机器ID
     */
    private String hostId;

    /**
     * 数据粒度
     */
    private Integer granularity;

    /**
     * 开始区间 (秒)
     */
    private Long startRange;

    /**
     * 结束区间 (秒)
     */
    private Long endRange;

    /**
     * 磁盘序列
     */
    private String seq;
}
