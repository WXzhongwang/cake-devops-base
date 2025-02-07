package com.rany.cake.devops.base.service.plugins.deploy;

import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.aggregate.Namespace;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.repository.AppRepository;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.integration.cloud.BaseCloudService;
import com.rany.cake.devops.base.service.integration.cloud.CloudFactory;
import com.rany.cake.devops.base.service.integration.cloud.KubernetesConstants;
import com.rany.cake.devops.base.service.integration.cloud.dto.CreateDeploymentCmd;
import com.rany.cake.devops.base.service.integration.cloud.dto.UpdateConfigMapCmd;
import com.rany.cake.devops.base.service.integration.cloud.dto.UpdateSecretCmd;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.annotation.PluginName;
import com.rany.cake.devops.base.service.utils.KubernetesUtils;
import com.rany.cake.devops.base.util.enums.DeployHistoryStatusEnum;
import com.rany.cake.toolkit.lang.time.Dates;
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
        UpdateConfigMapCmd createConfigMapCmd = new UpdateConfigMapCmd();
        createConfigMapCmd.setNamespace(namespace.getName().getName());
        createConfigMapCmd.setAppName(app.getAppName().getName());
        createConfigMapCmd.setEnvName(appEnv.getEnvName());
        createConfigMapCmd.setEnvId(appEnv.getEnvId());
        createConfigMapCmd.setConfigMap(appEnv.getConfigMap());
        createConfigMapCmd.setCurrentConfigMap(appEnv.getConfigMap());
        boolean configMapUpdated = cloudService.createOrUpdateConfigMap(context, createConfigMapCmd);
        if (!configMapUpdated) {
            log.error("更新ConfigMap失败");
            this.sendNotification(context, "更新ConfigMap失败", false);
            return false;
        }

        UpdateSecretCmd updateSecretCmd = new UpdateSecretCmd();
        updateSecretCmd.setNamespace(namespace.getName().getName());
        updateSecretCmd.setAppName(app.getAppName().getName());
        updateSecretCmd.setEnvName(appEnv.getEnvName());
        updateSecretCmd.setEnvId(appEnv.getEnvId());
        updateSecretCmd.setSecretMap(KubernetesUtils.convertSecretData(appEnv.getSecretMap()));
        updateSecretCmd.setCurrentSecretMap(KubernetesUtils.convertSecretData(appEnv.getSecretMap()));
        boolean secretMapUpdated = cloudService.createOrUpdateSecret(context, updateSecretCmd);
        if (!secretMapUpdated) {
            log.error("更新SecretMap失败");
            this.sendNotification(context, "更新SecretMap失败", false);
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
            this.sendNotification(context, "创建Deployment失败", false);
            return false;
        }
        appEnv.setDeployment(appEnv.getDeployment());
        appRepository.updateAppEnv(appEnv);
        context.getDeployHistory().setDeploymentName(appEnv.getDeployment());
        context.getDeployHistory().setEndTime(Dates.date());
        context.getDeployHistory().setDeployStatus(DeployHistoryStatusEnum.SUCCESS.getValue());
        return true;
    }
}
