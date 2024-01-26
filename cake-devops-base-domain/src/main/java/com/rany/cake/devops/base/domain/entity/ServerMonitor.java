package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 主机监控信息
 * author zhongshengwang
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ServerMonitor extends BaseEntity<Long> {

    private Long hostId;

    private Float cpuRate;

    private Float memRate;

    private Float oneMinLoadavg;

    private Float fiveMinLoadavg;

    private Float fifMinLoadavg;
}
