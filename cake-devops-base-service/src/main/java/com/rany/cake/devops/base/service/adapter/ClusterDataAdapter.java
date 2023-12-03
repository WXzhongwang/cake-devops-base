package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.ClusterDTO;
import com.rany.cake.devops.base.domain.aggregate.Cluster;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 应用
 *
 * @author zhongshengwang
 * @description 应用
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface ClusterDataAdapter extends BaseConvertor<Cluster, ClusterDTO> {


    /**
     * 聚合根转PO
     *
     * @param cluster 聚合根
     * @return PO
     */
    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "clusterName.name", target = "clusterName")
    @Mapping(source = "clusterType", target = "clusterType")
    ClusterDTO sourceToTarget(Cluster cluster);


    /**
     * PO转聚合根
     *
     * @param clusterDTO PO
     * @return 聚合根
     */

    @Mapping(target = "id.id", source = "id")
    @Mapping(target = "clusterName.name", source = "clusterName")
    @Mapping(target = "clusterType", source = "clusterType")
    Cluster targetToSource(ClusterDTO clusterDTO);
}
