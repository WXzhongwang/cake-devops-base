package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 主机环境变量
 *
 * @author zhongshengwang
 * @description 主机环境变量
 * @date 2023/8/24 22:03
 * @email 18668485565163.com
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class HostEnv extends BaseEntity<Long> {

    private String machineId;
    private String attrKey;
    private String attrValue;
    private String description;
}
