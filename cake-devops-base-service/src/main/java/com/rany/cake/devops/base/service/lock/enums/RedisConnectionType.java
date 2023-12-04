package com.rany.cake.devops.base.service.lock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Redis连接方式
 *
 * @author zhongshengwang
 */
@Getter
@AllArgsConstructor
public enum RedisConnectionType {
    /**
     * 单机部署方式(默认)
     */
    STANDALONE("standalone", "单机部署方式"),
    /**
     * 哨兵部署方式
     */
    SENTINEL("sentinel", "哨兵部署方式"),
    /**
     * 集群部署方式
     */
    CLUSTER("cluster", "集群方式"),
    /**
     * 主从部署方式
     */
    MASTERSLAVE("masterslave", "主从部署方式");

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;
}
