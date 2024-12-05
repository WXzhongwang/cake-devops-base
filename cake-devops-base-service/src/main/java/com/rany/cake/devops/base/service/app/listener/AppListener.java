package com.rany.cake.devops.base.service.app.listener;

import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.aggregate.Cluster;
import com.rany.cake.devops.base.domain.aggregate.Namespace;
import com.rany.cake.devops.base.domain.base.SnowflakeIdWorker;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.events.AppEnvCreateEvent;
import com.rany.cake.devops.base.domain.pk.ClusterId;
import com.rany.cake.devops.base.domain.pk.NamespaceId;
import com.rany.cake.devops.base.domain.repository.AppRepository;
import com.rany.cake.devops.base.domain.repository.ClusterRepository;
import com.rany.cake.devops.base.domain.repository.NameSpaceRepository;
import com.rany.cake.devops.base.domain.type.NamespaceName;
import com.rany.cake.devops.base.domain.valueobject.BusinessOwnership;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.integration.cloud.BaseCloudService;
import com.rany.cake.devops.base.service.integration.cloud.CloudFactory;
import com.rany.cake.devops.base.service.integration.cloud.dto.CreateNameSpaceCmd;
import io.kubernetes.client.openapi.models.V1Namespace;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AppListener {

    @Resource
    private AppRepository appRepository;
    @Resource
    private ClusterRepository clusterRepository;
    @Resource
    private CloudFactory cloudFactory;
    @Resource
    private NameSpaceRepository nameSpaceRepository;
    @Resource
    private SnowflakeIdWorker snowflakeIdWorker;

    @EventListener
    public void handleAppEnvCreateEvent(AppEnvCreateEvent appEnvCreateEvent) {
        App app = appRepository.find(appEnvCreateEvent.getAppId());
        BusinessOwnership businessOwnership = app.getBusinessOwnership();
        String department = businessOwnership.getDepartment();
        AppEnv appEnv = appRepository.getAppEnv(appEnvCreateEvent.getEnvId());
        ClusterId clusterId = appEnv.getClusterId();
        Cluster cluster = clusterRepository.find(clusterId);
        BaseCloudService cloudService = cloudFactory.build(cluster.getClusterType(),
                cluster.getConnectionString(), cluster.getToken());
        V1Namespace namespace = cloudService.getNamespace(department);
        if (namespace == null) {
            Namespace newNamespace = new Namespace(new NamespaceId(String.valueOf(snowflakeIdWorker.nextId())), new NamespaceName(department), clusterId);
            DeployContext context = new DeployContext();
            context.setNamespace(newNamespace);
            if (cloudService.createNameSpace(context, new CreateNameSpaceCmd(department))) {
                nameSpaceRepository.save(newNamespace);
            }
        }
    }
}
