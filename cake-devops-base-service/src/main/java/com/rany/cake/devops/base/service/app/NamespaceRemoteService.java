package com.rany.cake.devops.base.service.app;

import com.rany.cake.devops.base.api.command.namespace.CreateNamespaceCommand;
import com.rany.cake.devops.base.api.dto.NamespaceDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.api.query.namespace.NamespaceQuery;
import com.rany.cake.devops.base.api.service.NamespaceService;
import com.rany.cake.devops.base.domain.aggregate.Cluster;
import com.rany.cake.devops.base.domain.aggregate.Namespace;
import com.rany.cake.devops.base.domain.base.SnowflakeIdWorker;
import com.rany.cake.devops.base.domain.pk.ClusterId;
import com.rany.cake.devops.base.domain.pk.NamespaceId;
import com.rany.cake.devops.base.domain.service.ClusterDomainService;
import com.rany.cake.devops.base.domain.service.NamespaceDomainService;
import com.rany.cake.devops.base.domain.type.NamespaceName;
import com.rany.cake.devops.base.service.adapter.NamespaceDataAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;

import java.util.List;

/**
 * 应用服务
 *
 * @author zhongshengwang
 * @description 应用服务
 * @date 2023/1/28 20:40
 * @email 18668485565163.com
 */
@Service
@ShenyuService("/namespace/**")
@Slf4j
@AllArgsConstructor
public class NamespaceRemoteService implements NamespaceService {
    private final SnowflakeIdWorker snowflakeIdWorker;
    private final ClusterDomainService clusterDomainService;
    private final NamespaceDomainService namespaceDomainService;
    private final NamespaceDataAdapter namespaceDataAdapter;


    @Override
    public String createNamespace(CreateNamespaceCommand createNamespaceCommand) {
        Cluster cluster = clusterDomainService.getCluster(new ClusterId(createNamespaceCommand.getClusterId()));
        if (cluster == null) {
            throw new DevOpsException(DevOpsErrorMessage.CLUSTER_NOT_FOUND);
        }
        Namespace namespace = new Namespace(new NamespaceId(String.valueOf(snowflakeIdWorker.nextId())),
                new NamespaceName(createNamespaceCommand.getName()),
                new ClusterId(createNamespaceCommand.getClusterId()));
        if (createNamespaceCommand.getRequestCpu() != null) {
            namespace.setRequestCpu(createNamespaceCommand.getRequestCpu());
        }
        if (createNamespaceCommand.getRequestMemory() != null) {
            namespace.setRequestMemory(createNamespaceCommand.getRequestMemory());
        }
        if (createNamespaceCommand.getLimitCpu() != null) {
            namespace.setLimitCpu(createNamespaceCommand.getLimitCpu());
        }
        if (createNamespaceCommand.getLimitMemory() != null) {
            namespace.setLimitMemory(createNamespaceCommand.getLimitMemory());
        }
        if (createNamespaceCommand.getMaxPods() != null) {
            namespace.setMaxPods(createNamespaceCommand.getMaxPods());
        }
        if (createNamespaceCommand.getMaxGpu() != null) {
            namespace.setMaxGpu(createNamespaceCommand.getMaxGpu());
        }
        if (createNamespaceCommand.getMaxServices() != null) {
            namespace.setMaxServices(createNamespaceCommand.getMaxServices());
        }
        namespace.init();
        namespaceDomainService.save(namespace);
        return namespace.getNamespaceId().getNamespaceId();
    }

    @Override
    public List<NamespaceDTO> listNamespace(NamespaceQuery namespaceQuery) {
        Cluster cluster = clusterDomainService.getCluster(new ClusterId(namespaceQuery.getClusterId()));
        if (cluster == null) {
            throw new DevOpsException(DevOpsErrorMessage.CLUSTER_NOT_FOUND);
        }
        List<Namespace> namespaces = namespaceDomainService.listNamespace(cluster.getClusterId());
        return namespaceDataAdapter.sourceToTarget(namespaces);
    }
}
