package com.rany.cake.devops.base.domain.service;

import com.rany.cake.devops.base.domain.aggregate.Cluster;
import com.rany.cake.devops.base.domain.pk.ClusterId;
import com.rany.cake.devops.base.domain.repository.ClusterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 集群
 *
 * @author zhongshengwang
 * @description 集群
 * @date 2023/2/18 22:40
 * @email 18668485565163.com
 */
@Component
@AllArgsConstructor
public class ClusterDomainService {
    private final ClusterRepository clusterRepository;


    /**
     * 创建集群
     *
     * @param cluster
     */
    public void save(Cluster cluster) {
        clusterRepository.save(cluster);
    }

    public void update(Cluster cluster) {
        clusterRepository.update(cluster);
    }

    public Cluster getCluster(ClusterId clusterId) {
        return clusterRepository.find(clusterId);
    }

    public List<Cluster> selectAll() {
        return clusterRepository.selectAll();
    }
}
