package com.rany.cake.devops.base.domain.aggegrate;

import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.cake.devops.base.api.enums.ClusterTypeEnum;
import com.rany.cake.devops.base.domain.entity.ClusterExtend;
import com.rany.cake.devops.base.domain.pk.ClusterId;
import com.rany.cake.devops.base.domain.type.ClusterName;
import lombok.*;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
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
    private ClusterExtend clusterExtend;

    public Cluster(ClusterId id, ClusterName clusterName, ClusterTypeEnum clusterType) {
        this.id = id;
        this.clusterName = clusterName;
        this.clusterType = clusterType;
    }
}
