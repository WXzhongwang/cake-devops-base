package com.rany.cake.devops.base.service.plugins.deploy;

import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.aggregate.Namespace;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.repository.AppRepository;
import com.rany.cake.devops.base.service.cloud.BaseCloudService;
import com.rany.cake.devops.base.service.cloud.CloudFactory;
import com.rany.cake.devops.base.service.cloud.KubernetesConstants;
import com.rany.cake.devops.base.service.cloud.dto.CreateDeploymentCmd;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.annotation.PluginName;
import com.rany.cake.devops.base.util.enums.DeployHistoryStatusEnum;
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
        App app = context.getApp();
        BaseCloudService cloudService = cloudFactory.build(context.getCluster().getClusterType(),
                context.getCluster().getConnectionString(), context.getCluster().getToken());
        V1Namespace deployNamespace = cloudService.getNamespace(namespace.getName().getName());
        if (deployNamespace == null) {
            log.error("命名空间未找到：{}", namespace.getName().getName());
            return false;
        }
        CreateDeploymentCmd createDeploymentCmd = new CreateDeploymentCmd();
        createDeploymentCmd.setDeploymentName(app.getAppName().getName());
        createDeploymentCmd.setAppName(app.getAppName().getName());
        createDeploymentCmd.setDeploymentImage(context.getDeploymentImage());
        createDeploymentCmd.setHealthCheck(context.getDeploymentImage());
        createDeploymentCmd.setEnvVars(appEnv.getEnvVars());
        createDeploymentCmd.setResourceStrategy(appEnv.getResourceStrategy());
        createDeploymentCmd.setVolumeMounts(app.getVolumeMounts());
        createDeploymentCmd.setContainerPort(KubernetesConstants.DEFAULT_WEB_SERVICE_PORT);
        createDeploymentCmd.setNamespace(namespace.getName().getName());

        boolean deploymentCreated = cloudService.createOrUpdateDeployment(context,
                createDeploymentCmd);
        if (!deploymentCreated) {
            log.error("创建Deployment失败");
            return false;
        }
        appEnv.setDeployment(appEnv.getDeployment());
        appRepository.updateAppEnv(appEnv);
        context.getDeployHistory().setDeploymentName(appEnv.getDeployment());
        context.getDeployHistory().setDeployStatus(DeployHistoryStatusEnum.SUCCESS.getCode());
        return true;
    }
}
