package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.aggregate.Cluster;
import com.rany.cake.devops.base.domain.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.domain.pk.ClusterId;
import com.rany.cake.devops.base.domain.repository.ClusterRepository;
import com.rany.cake.devops.base.infra.convertor.ClusterDataConvertor;
import com.rany.cake.devops.base.infra.dao.ClusterDao;
import com.rany.cake.devops.base.infra.mapper.ClusterPOMapper;
import com.rany.cake.devops.base.infra.po.ClusterPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 集群
 *
 * @author zhongshengwang
 * @description 集群
 * @date 2023/1/28 21:02
 * @email 18668485565163.com
 */
@Service
@AllArgsConstructor
public class ClusterRepositoryImpl implements ClusterRepository {

    private final ClusterPOMapper clusterPOMapper;
    private final ClusterDao clusterDao;
    private final ClusterDataConvertor clusterDataConvertor;

    @Override
    public Cluster find(@NotNull ClusterId clusterId) {
        ClusterPO clusterPO = clusterPOMapper.selectByPrimaryKey(clusterId.getId());
        return clusterDataConvertor.targetToSource(clusterPO);
    }

    @Override
    public void remove(@NotNull Cluster app) {
        ClusterPO clusterPO = clusterDataConvertor.sourceToTarget(app);
        clusterPO.setIsDeleted(DeleteStatusEnum.YES.getValue());
        clusterPOMapper.updateByPrimaryKey(clusterPO);
    }

    @Override
    public void save(@NotNull Cluster app) {
        clusterDao.save(app);
    }

    @Override
    public int update(Cluster cluster) {
        return clusterDao.update(cluster);
    }

    public List<Cluster> selectAll() {
        return clusterDao.selectAll();
    }
}
