package com.rany.cake.devops.base.service.cloud;

import com.rany.cake.devops.base.domain.type.AppName;
import com.rany.cake.devops.base.domain.valueobject.ResourceStrategy;
import com.rany.cake.devops.base.domain.valueobject.VolumeMount;
import com.rany.cake.devops.base.service.context.DeployContext;
import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.custom.Quantity;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/20 19:46
 * @email 18668485565163.com
 */
@Slf4j
public abstract class BaseCloudService {


    /**
     * k8s-api客户端
     */
    protected ApiClient apiClient;


    /**
     * 构建集群POD内通过SA访问的客户端
     * loading the in-cluster config, including:
     * 1. service-account CA
     * 2. service-account bearer-token
     * 3. service-account namespace
     * 4. master endpoints(ip, port) from pre-set environment variables
     */
    protected void build() {
        try {
            this.apiClient = ClientBuilder.cluster().build();
        } catch (IOException e) {
            log.error("构建K8s-Client异常", e);
            throw new RuntimeException("构建K8s-Client异常");
        }
    }


    protected void build(String connectionString, String token) {
        if (StringUtils.isEmpty(connectionString) || StringUtils.isEmpty(token)) {
            build();
            return;
        }
        this.apiClient = Config.fromToken(connectionString, token, false);
    }

    /**
     * createDeployment
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean createDeployment(DeployContext context);

    /**
     * deleteDeployment
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean deleteDeployment(DeployContext context);


    /**
     * createService
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean createService(DeployContext context);


    /**
     * updateService
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean updateService(DeployContext context);

    /**
     * deleteService 删除服务
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean deleteService(DeployContext context);

    /**
     * 创建configMap
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean createConfigMap(DeployContext context);

    /**
     * 更新configMap
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean updateConfigMap(DeployContext context);

    public abstract boolean createIngress(DeployContext context);

    public abstract boolean deleteIngress(DeployContext context);

    protected V1Deployment createBasicDeployment(DeployContext context) {
        AppName appName = context.getApp().getAppName();
        String healthCheck = context.getApp().getHealthCheck();
        Map<String, String> configMap = context.getAppEnv().getConfigMap();
        List<VolumeMount> volumeMounts = context.getApp().getVolumeMounts();
        String envName = context.getAppEnv().getEnvName();
        ResourceStrategy resourceStrategy = context.getAppEnv().getResourceStrategy();
        String configMapName = String.format("%s-%s", appName, envName);
        // 配置configMap
        List<V1EnvVar> configMapValues = new ArrayList<>();
        if (configMap != null && !configMap.isEmpty()) {
            for (Map.Entry<String, String> entry : configMap.entrySet()) {
                V1EnvVar envVar = new V1EnvVar()
                        .name(entry.getKey()) // 环境变量名称
                        .valueFrom(new V1EnvVarSource()
                                .configMapKeyRef(new V1ConfigMapKeySelector()
                                        .name(configMapName)
                                        .key(entry.getKey())));
                configMapValues.add(envVar);
            }
        }
        // 设置资源
        Map<String, Quantity> request = new HashMap<>();
        request.put("cpu", new Quantity(resourceStrategy.getCpu()));
        request.put("memory", new Quantity(resourceStrategy.getMemory()));
        Map<String, Quantity> limit = new HashMap<>();
        limit.put("cpu", new Quantity(resourceStrategy.getMaxCpu()));
        limit.put("memory", new Quantity(resourceStrategy.getMaxMemory()));
        V1ResourceRequirements requirements = new V1ResourceRequirements()
                .limits(request)
                .requests(limit);

        List<V1Volume> dataVolumes = new ArrayList<>();
        List<V1VolumeMount> dataMounts = new ArrayList<>();
        for (VolumeMount volumeMount : volumeMounts) {
            // 创建 Volume 对象
            V1Volume volume = new V1Volume()
                    .name(volumeMount.getName())
                    .hostPath(new V1HostPathVolumeSource().path(volumeMount.getPath()));
            dataVolumes.add(volume);

            // 创建 VolumeMount 挂载对象
            V1VolumeMount mount = new V1VolumeMount()
                    .name(volumeMount.getName())
                    .mountPath(volumeMount.getMountPath());
            dataMounts.add(mount);
        }

        // 创建一个简单的 Deployment 对象
        return new V1Deployment()
                .metadata(new V1ObjectMeta().name(context.getDeploymentName()))
                .spec(new V1DeploymentSpec()
                        .replicas(resourceStrategy.getReplicas())
                        .selector(new V1LabelSelector().matchLabels(Collections.singletonMap("app", appName.getName())))
                        .template(new V1PodTemplateSpec()
                                .metadata(new V1ObjectMeta().labels(Collections.singletonMap("app", appName.getName())))
                                // 创建一个新的 Pod 规格对象 (V1PodSpec)，该对象包含了定义 Pod 的各种配置信息，如容器、卷挂载、标签选择器等。
                                .spec(new V1PodSpec()
                                        .containers(Collections.singletonList(
                                                new V1Container()
                                                        .name(appName.getName())
                                                        .image(context.getDeploymentImage())
                                                        .env(configMapValues)
                                                        .resources(requirements)
                                                        .ports(Collections.singletonList(new V1ContainerPort().containerPort(context.getContainerPort())))
                                                        // 存活性探针（Liveness Probe）
                                                        // 存活性探针用于确定容器是否存活。如果存活性探针失败，Kubernetes 将尝试重新启动容器。
                                                        .livenessProbe(
                                                                new V1Probe()
                                                                        .httpGet(new V1HTTPGetAction()
                                                                                .path(healthCheck)
                                                                                // 替换为你的健康检查路径
                                                                                .port(new IntOrString(context.getContainerPort())))
                                                                        .initialDelaySeconds(3)  // 初始延迟
                                                                        .periodSeconds(3)  // 探测周期
                                                                        .timeoutSeconds(1)  // 超时时间
                                                                        .successThreshold(1)  // 成功阈值
                                                                        .failureThreshold(3)  // 失败阈值
                                                        )
                                                        .volumeMounts(dataMounts)
                                        )).volumes(dataVolumes)
                                )
                        )
                );
    }
}
