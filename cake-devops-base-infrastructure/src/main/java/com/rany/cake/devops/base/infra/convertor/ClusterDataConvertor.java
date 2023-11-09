package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.aggregate.Cluster;
import com.rany.cake.devops.base.infra.po.ClusterPO;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "clusterName.name", target = "name")
    @Mapping(expression = "java(com.rany.cake.devops.base.infra.convertor.ClusterDataConvertor.toTagsString(cluster))", target = "tags")
    @Override
    ClusterPO sourceToTarget(Cluster cluster);

    /**
     * PO转聚合根
     *
     * @param clusterPO PO
     * @return 聚合根
     */

    @Override
    @Mapping(target = "id.id", source = "id")
    @Mapping(target = "clusterName.name", source = "name")
    @Mapping(target = "tags", expression = "java(com.rany.cake.devops.base.infra.convertor.ClusterDataConvertor.toTagList(clusterPO))")
    Cluster targetToSource(ClusterPO clusterPO);


    static String toTagsString(Cluster cluster) {
        if (cluster != null) {
            List<String> tags = cluster.getTags();
            return StringUtils.join(tags, ",");
        }
        return null;
    }

    static List<String> toTagList(ClusterPO clusterPO) {
        if (clusterPO != null && StringUtils.isNotBlank(clusterPO.getTags())) {
            String[] split = clusterPO.getTags().split(",");
            return Arrays.stream(split).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
