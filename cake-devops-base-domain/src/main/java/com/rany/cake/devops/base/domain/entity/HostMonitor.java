package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 主机监控配置信息
 * author zhongshengwang
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class HostMonitor extends BaseEntity<Long> {

    private Long hostId;
    private Integer monitorStatus;
    private String monitorUrl;
    private String accessToken;
    private String agentVersion;
}
