package com.rany.cake.devops.base.domain.aggegrate;

import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.cake.devops.base.domain.pk.NameSpaceId;
import com.rany.cake.devops.base.domain.type.NameSpaceName;
import lombok.*;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 17:15
 * @email 18668485565163.com
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NameSpace extends BaseAggregateRoot implements IAggregate<NameSpaceId> {

    private NameSpaceId id;

    private NameSpaceName name;

    /**
     * 所有容器的 CPU 请求总额不得超过 x CPU。
     */
    private Double requestCpu;
    /**
     * 所有容器的内存请求总额不得超过 x GiB。
     */
    private Double requestMemory;
    /**
     * 所有容器的 CPU 限额总额不得超过 x CPU。
     */
    private Double limitCpu;
    /**
     * 所有容器的内存限额总额不得超过 x GiB。
     */
    private Double limitMemory;

    /**
     * 最多创建x个Pods
     */
    private Integer maxPods;
    /**
     * 最多使用 x GPU
     */
    private Integer maxGpu;
    /**
     * 最多创建x个services
     */
    private Integer maxServices;
    
    private String status;

    public NameSpace(NameSpaceId id, NameSpaceName name) {
        this.id = id;
        this.name = name;
    }
}
