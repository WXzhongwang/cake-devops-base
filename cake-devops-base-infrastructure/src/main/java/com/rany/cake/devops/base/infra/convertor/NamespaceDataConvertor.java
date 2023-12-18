package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.aggregate.Namespace;
import com.rany.cake.devops.base.infra.po.NamespacePO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 命名空间
 *
 * @author zhongshengwang
 * @description 命名空间
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface NamespaceDataConvertor extends BaseConvertor<Namespace, NamespacePO> {


    /**
     * 聚合根转PO
     *
     * @param namespace 聚合根
     * @return PO
     */
    @Mapping(source = "namespaceId.namespaceId", target = "namespaceId")
    @Mapping(source = "clusterId.clusterId", target = "clusterId")
    @Mapping(source = "name.name", target = "name")
    @Override
    NamespacePO sourceToTarget(Namespace namespace);

    /**
     * PO转聚合根
     *
     * @param namespacePO PO
     * @return 聚合根
     */

    @Override
    @Mapping(target = "namespaceId.namespaceId", source = "namespaceId")
    @Mapping(target = "clusterId.clusterId", source = "clusterId")
    @Mapping(target = "name.name", source = "name")
    Namespace targetToSource(NamespacePO namespacePO);
}
