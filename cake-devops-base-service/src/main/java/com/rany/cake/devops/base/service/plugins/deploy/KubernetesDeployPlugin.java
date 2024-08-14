package com.rany.cake.devops.base.service.plugins.deploy;

import com.rany.cake.devops.base.domain.aggregate.Namespace;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.repository.AppRepository;
import com.rany.cake.devops.base.service.cloud.BaseCloudService;
import com.rany.cake.devops.base.service.cloud.CloudFactory;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.annotation.PluginName;
import io.kubernetes.client.openapi.models.V1Namespace;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhongshengwang
 */
@Component
@PluginName("K8S容器部署")
public class KubernetesDeployPlugin extends BasePlugin {
    @Resource
    private CloudFactory cloudFactory;
    @Resource
    private AppRepository appRepository;

    @Override
    public boolean init(DeployContext context) {
        return true;
    }

    @Override
    public boolean execute(DeployContext context) {
        Namespace namespace = context.getNamespace();
        AppEnv appEnv = context.getAppEnv();
        BaseCloudService cloudService = cloudFactory.build(context.getCluster().getClusterType(),
                context.getCluster().getConnectionString(), context.getCluster().getToken());
        V1Namespace deployNamespace = cloudService.getNamespace(context);
        if (deployNamespace == null) {
            log.error("命名空间未找到：{}", namespace.getName().getName());
            return false;
        }
        boolean deploymentCreated = cloudService.createOrUpdateDeployment(context);
        if (!deploymentCreated) {
            log.error("创建Deployment失败");
            return false;
        }
        appEnv.setDeploymentName(context.getDeploymentName());
        appRepository.updateAppEnv(appEnv);

//        boolean serviceCreated = cloudService.createService(context);
//        if (!serviceCreated) {
//            log.error("创建Service失败");
//            return false;
//        }
//        appEnv.setServiceName(context.getServiceName());
//        appRepository.updateAppEnv(appEnv);

//        boolean ingressCreated = cloudService.createOrUpdateIngress(context);
//        if (!ingressCreated) {
//            log.error("创建Ingress失败");
//            return false;
//        }
//        appEnv.setIngressName(context.getIngressName());
//        appRepository.updateAppEnv(appEnv);
        return true;
    }
}
