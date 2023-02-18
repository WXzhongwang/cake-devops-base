package com.rany.cake.devops.base.domain.convertor;

import com.rany.cake.devops.base.domain.aggregate.Cluster;
import com.rany.cake.devops.base.infra.po.ClusterPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface ClusterDataConvertor extends BaseConvertor<Cluster, ClusterPO> {


    /**
     * 聚合根转PO
     *
     * @param cluster
     * @return
     */
    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "clusterName.name", target = "name")
    @Override
    ClusterPO sourceToTarget(Cluster cluster);

    /**
     * PO转聚合根
     *
     * @param clusterPO
     * @return
     */

    @Override
    @Mapping(target = "id.id", source = "id")
    @Mapping(target = "clusterName.name", source = "name")
    Cluster targetToSource(ClusterPO clusterPO);
}
