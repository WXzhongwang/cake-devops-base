package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.aggregate.Cluster;
import com.rany.cake.devops.base.infra.po.ClusterPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 集群转换器
 *
 * @author zhongshengwang
 * @description 集群转换器
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface ClusterDataConvertor extends BaseConvertor<Cluster, ClusterPO> {


    /**
     * 聚合根转PO
     *
     * @param cluster 聚合根
     * @return PO
     */
    @Mapping(source = "clusterId.clusterId", target = "clusterId")
    @Mapping(source = "clusterName.name", target = "name")
    @Mapping(expression = "java(this.convertString(cluster.getTags()))", target = "tags")
    @Override
    ClusterPO sourceToTarget(Cluster cluster);

    /**
     * PO转聚合根
     *
     * @param clusterPO PO
     * @return 聚合根
     */

    @Override
    @Mapping(target = "clusterId.clusterId", source = "clusterId")
    @Mapping(target = "clusterName.name", source = "name")
    @Mapping(target = "tags", expression = "java(this.convertList(clusterPO.getTags()))")
    Cluster targetToSource(ClusterPO clusterPO);
}
