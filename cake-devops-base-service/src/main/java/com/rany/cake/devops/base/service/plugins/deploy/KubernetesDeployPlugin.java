package com.rany.cake.devops.base.service.plugins.deploy;

import com.rany.cake.devops.base.domain.aggregate.Namespace;
import com.rany.cake.devops.base.service.cloud.BaseCloudService;
import com.rany.cake.devops.base.service.cloud.CloudFactory;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.annotation.PluginName;
import io.kubernetes.client.openapi.models.V1Namespace;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@PluginName("K8S容器部署")
public class KubernetesDeployPlugin extends BasePlugin {
    @Resource
    private CloudFactory cloudFactory;

    @Override
    public boolean init(DeployContext context) {
        return true;
    }

    @Override
    public boolean execute(DeployContext context) {
        Namespace namespace = context.getNamespace();
        BaseCloudService cloudService = cloudFactory.build(context.getCluster().getClusterType(),
                context.getCluster().getConnectionString(), context.getCluster().getToken());
        V1Namespace deployNamespace = cloudService.getNamespace(context);
        if (deployNamespace == null) {
            log.error("命名空间未找到：{}", namespace.getName());
            return false;
        }
        boolean deployment = cloudService.createDeployment(context);
        return false;
    }
}
