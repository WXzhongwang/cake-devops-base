package com.rany.cake.devops.base.domain.repository.impl;

import com.rany.cake.devops.base.domain.aggregate.Cluster;
import com.rany.cake.devops.base.domain.pk.ClusterId;
import com.rany.cake.devops.base.domain.repository.ClusterRepository;
import com.rany.cake.devops.base.infra.mapper.ClusterPOMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/28 21:02
 * @email 18668485565163.com
 */
@Service
@AllArgsConstructor
public class ClusterRepositoryImpl implements ClusterRepository {

    @Autowired
    private ClusterPOMapper clusterPOMapper;

    @Override

    public Cluster find(@NotNull ClusterId clusterId) {
        return null;
    }

    @Override
    public void remove(@NotNull Cluster app) {

    }

    @Override
    public void save(@NotNull Cluster app) {

    }
}
