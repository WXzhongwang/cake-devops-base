package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 主机拓展信息
 *
 * @author zhongshengwang
 * @description 主机拓展信息
 * @date 2023/8/24 22:10
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HostExtend extends BaseEntity<Long> {
    private Long hostId;
    private String instantId;
    private String zoneId;
    private Integer cpu;
    private Double memory;
    private String disk;
    private String osName;
    private String osType;
    private String privateIpAddress;
    private String publicIpAddress;
    private String instanceChargeType;
    private String internetChargeType;
    private Date expiredTime;

}
