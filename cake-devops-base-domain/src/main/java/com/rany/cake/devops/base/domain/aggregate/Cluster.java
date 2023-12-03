package com.rany.cake.devops.base.domain.aggregate;

import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.cake.devops.base.domain.entity.ClusterExtend;
import com.rany.cake.devops.base.domain.enums.ClusterTypeEnum;
import com.rany.cake.devops.base.domain.pk.ClusterId;
import com.rany.cake.devops.base.domain.type.ClusterName;
import lombok.*;

import java.util.List;

/**
 * 集群
 *
 * @author zhongshengwang
 * @description 集群
 * @date 2023/1/15 16:20
 * @email 18668485565163.com
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Cluster extends BaseAggregateRoot implements IAggregate<ClusterId> {

    private ClusterId id;
    private ClusterName clusterName;
    private List<String> tags;
    private String version;
    private ClusterTypeEnum clusterType;
    private String connectionString;
    private String token;
    private ClusterExtend clusterExtend;
    /**
     * 命名空间
     */
    private List<Namespace> namespaces;

    private String status;

    public Cluster(ClusterId id, ClusterName clusterName, ClusterTypeEnum clusterType) {
        this.id = id;
        this.clusterName = clusterName;
        this.clusterType = clusterType;
    }
}
