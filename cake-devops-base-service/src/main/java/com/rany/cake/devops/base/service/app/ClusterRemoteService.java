package com.rany.cake.devops.base.service.app;

import com.rany.cake.devops.base.api.command.cluster.CreateClusterCommand;
import com.rany.cake.devops.base.api.command.cluster.TestClusterConnectCommand;
import com.rany.cake.devops.base.api.dto.ClusterDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.api.service.ClusterService;
import com.rany.cake.devops.base.domain.aggregate.Cluster;
import com.rany.cake.devops.base.domain.base.SnowflakeIdWorker;
import com.rany.cake.devops.base.domain.pk.ClusterId;
import com.rany.cake.devops.base.domain.service.ClusterDomainService;
import com.rany.cake.devops.base.domain.type.ClusterName;
import com.rany.cake.devops.base.service.adapter.ClusterDataAdapter;
import com.rany.cake.devops.base.util.enums.ClusterTypeEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * 集群相关
 *
 * @author zhongshengwang
 * @description 集群相关
 * @date 2023/2/18 22:19
 * @email 18668485565163.com
 */
@Service
// @ShenyuService("/cluster/**")
@Slf4j
@AllArgsConstructor
public class ClusterRemoteService implements ClusterService {
    private final SnowflakeIdWorker idGenerator;

    private final ClusterDomainService clusterDomainService;
    private final ClusterDataAdapter clusterDataAdapter;

    @Override
    public Boolean testConnect(TestClusterConnectCommand testClusterConnectCommand) {
        ClusterTypeEnum clusterTypeEnum = EnumUtils.getEnum(ClusterTypeEnum.class, testClusterConnectCommand.getClusterType());
        if (clusterTypeEnum == null) {
            throw new DevOpsException(DevOpsErrorMessage.OPS_SUPPORTED_ERROR);
        }
        // TODO: 缺少实现
        return Boolean.TRUE;
    }

    @Override
    public String createCluster(CreateClusterCommand createClusterCommand) {
        Boolean connect = this.testConnect(new TestClusterConnectCommand(createClusterCommand.getConnectString(),
                createClusterCommand.getClusterType(), createClusterCommand.getToken()));
        if (!connect) {
            throw new DevOpsException(DevOpsErrorMessage.OPS_CONNECTED_ERROR);
        }
        Cluster cluster = new Cluster(new ClusterId(String.valueOf(idGenerator.nextId())), new ClusterName(createClusterCommand.getName()),
                EnumUtils.getEnum(ClusterTypeEnum.class, createClusterCommand.getClusterType()));
        cluster.setConnectionString(createClusterCommand.getConnectString());
        cluster.setToken(createClusterCommand.getToken());
        cluster.setTags(createClusterCommand.getTags());
        clusterDomainService.save(cluster);
        return cluster.getClusterId().getClusterId();
    }

    @Override
    public List<ClusterDTO> listCluster() {
        List<Cluster> clusters = clusterDomainService.selectAll();
        return clusterDataAdapter.sourceToTarget(clusters);
    }
}
