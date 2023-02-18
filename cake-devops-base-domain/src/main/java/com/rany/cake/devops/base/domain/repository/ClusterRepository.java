package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.ddd.repository.Repository;
import com.rany.cake.devops.base.domain.aggregate.Cluster;
import com.rany.cake.devops.base.domain.pk.ClusterId;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/28 21:01
 * @email 18668485565163.com
 */
public interface ClusterRepository extends Repository<Cluster, ClusterId> {
}
