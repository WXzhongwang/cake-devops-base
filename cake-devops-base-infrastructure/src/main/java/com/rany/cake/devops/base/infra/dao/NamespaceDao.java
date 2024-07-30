package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.aggregate.Namespace;
import com.rany.cake.devops.base.infra.po.NamespacePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 命名空间
 *
 * @author zhongshengwang
 * @description 发布
 * @date 2023/2/2 21:24
 * @email 18668485565163.com
 */
public interface NamespaceDao {


    /**
     * 新增
     *
     * @param namespace 命名空间
     * @return
     */
    int save(Namespace namespace);


    /**
     * 更新
     *
     * @param namespace 命名空间
     * @return
     */
    int update(Namespace namespace);

    NamespacePO findByCluster(@Param("clusterId") String clusterId, @Param("namespace") String namespace);

    /**
     * 查看集群命名空间
     *
     * @param clusterId 集群ID
     * @return 命名空间列表
     */
    List<NamespacePO> listNamespace(@Param("clusterId") String clusterId);

    NamespacePO selectByNamespaceId(@Param("namespaceId") String namespaceId);

}
