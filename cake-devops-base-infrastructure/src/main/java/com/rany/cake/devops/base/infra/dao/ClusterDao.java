package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.aggregate.Cluster;

/**
 * 集群
 *
 * @author zhongshengwang
 * @description 集群
 * @date 2023/2/2 21:24
 * @email 18668485565163.com
 */
public interface ClusterDao {


    /**
     * 新增
     *
     * @param cluster 集群
     * @return
     */
    int save(Cluster cluster);


    /**
     * 更新
     *
     * @param cluster 集群
     * @return
     */
    int update(Cluster cluster);

}
