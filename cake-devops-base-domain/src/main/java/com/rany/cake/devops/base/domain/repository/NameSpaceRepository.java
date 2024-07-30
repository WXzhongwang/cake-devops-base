package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.ddd.repository.Repository;
import com.rany.cake.devops.base.domain.aggregate.Namespace;
import com.rany.cake.devops.base.domain.pk.ClusterId;
import com.rany.cake.devops.base.domain.pk.NamespaceId;

import java.util.List;

/**
 * 命名空间
 *
 * @author zhongshengwang
 * @description 命名空间
 * @date 2023/1/28 21:01
 * @email 18668485565163.com
 */
public interface NameSpaceRepository extends Repository<Namespace, NamespaceId> {

    int update(Namespace namespace);

    Namespace findByCluster(String clusterId, String namespace);

    List<Namespace> listNamespace(ClusterId clusterId);
}
