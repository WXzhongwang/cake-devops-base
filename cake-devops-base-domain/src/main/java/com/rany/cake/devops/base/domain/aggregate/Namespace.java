package com.rany.cake.devops.base.domain.aggregate;

import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.cake.devops.base.domain.enums.CommonStatusEnum;
import com.rany.cake.devops.base.domain.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.domain.pk.ClusterId;
import com.rany.cake.devops.base.domain.pk.NamespaceId;
import com.rany.cake.devops.base.domain.type.NamespaceName;
import lombok.*;

import java.util.Date;

/**
 * 命名空间
 *
 * @author zhongshengwang
 * @description 命名空间
 * @date 2023/1/15 17:15
 * @email 18668485565163.com
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Namespace extends BaseAggregateRoot implements IAggregate<NamespaceId> {

    private NamespaceId namespaceId;

    private ClusterId clusterId;

    private NamespaceName name;

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

    public Namespace(NamespaceId namespaceId, NamespaceName name, ClusterId clusterId) {
        this.namespaceId = namespaceId;
        this.name = name;
        this.clusterId = clusterId;
    }

    public void init() {
        this.status = CommonStatusEnum.ENABLE.getCode();
        this.isDeleted = DeleteStatusEnum.NO.getValue();
        this.gmtCreate = new Date();
        this.gmtModified = new Date();
    }


    @Override
    public NamespaceId getBizID() {
        return namespaceId;
    }
}
