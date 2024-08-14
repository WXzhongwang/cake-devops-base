package com.rany.cake.devops.base.service.cloud;

import com.alibaba.fastjson.JSON;
import com.rany.cake.devops.base.domain.type.AppName;
import com.rany.cake.devops.base.domain.valueobject.ResourceStrategy;
import com.rany.cake.devops.base.domain.valueobject.VolumeMount;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.toolkit.lang.utils.Lists;
import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.custom.Quantity;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.apis.NetworkingV1Api;
import io.kubernetes.client.openapi.models.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * K8sCloudService
 *
 * @author zhongshengwang
 * @description 发布服务
 * @date 2023/1/20 20:51
 * @email 18668485565163.com
 */
@Slf4j
public class K8sCloudService extends BaseCloudService {

    public K8sCloudService(String connectString, String token) {
        build(connectString, token);
    }

    @Override
    public boolean testConnection(DeployContext context) {
        try {
            // 创建 CoreV1Api 实例
            CoreV1Api coreV1Api = new CoreV1Api(apiClient);

            // 获取所有 Pods 列表
            V1PodList podList = coreV1Api.listPodForAllNamespaces(true,
                    null, null, null, null, null, null, null, null, null);
            if (podList == null) {
                log.error("Failed to list pods.");
                return false;
            }
            // 打印 Pod 信息
            for (V1Pod pod : podList.getItems()) {
                log.info("Pod Name: " + Objects.requireNonNull(pod.getMetadata()).getName());
                log.info("Namespace: " + pod.getMetadata().getNamespace());
                log.info("Status: " + Objects.requireNonNull(pod.getStatus()).getPhase());
                log.info("--------------------------------");
            }
            return !podList.getItems().isEmpty();
        } catch (ApiException e) {
            log.error("Failed to list pods.", e);
            return false;
        }

    }

    @Override
    public boolean createDeployment(DeployContext context) {
        String namespace = context.getNamespace().getName().getName();
        // 创建 Deployment
        V1Deployment deployment = createBasicDeployment(context);
        try {
            AppsV1Api apiInstance = new AppsV1Api(apiClient);
            apiInstance.createNamespacedDeployment(namespace, deployment, null, null, null, null);
            log.info("Deployment created successfully.");
            return true;
        } catch (ApiException e) {
            log.error("Failed to create Deployment. {}", e.getResponseBody(), e);
            return false;
        }
    }

    @Override
    public boolean createOrUpdateDeployment(DeployContext context) {
        String namespace = context.getNamespace().getName().getName();
        String deploymentName = context.getDeploymentName();
        // 假设这里有获取Deployment名称的方法
        // 创建 Deployment
        V1Deployment deployment = createBasicDeployment(context);
        try {
            AppsV1Api apiInstance = new AppsV1Api(apiClient);
            // 环境内已经设置过deploymentName
            boolean existingDeployment = StringUtils.isNoneBlank(context.getAppEnv().getDeploymentName());
            if (existingDeployment) {
                // 如果存在，则更新
                apiInstance.replaceNamespacedDeployment(deploymentName, namespace, deployment, null, null, null, null);
                log.info("Deployment {} updated successfully.", deploymentName);
            } else {
                // 如果不存在，则创建
                apiInstance.createNamespacedDeployment(namespace, deployment, null, null, null, null);
                log.info("Deployment {} created successfully.", deploymentName);
            }
            return true;
        } catch (ApiException e) {
            log.error("Failed to create or update Deployment {}. {}", deploymentName, e.getResponseBody(), e);
            return false;
        }
    }


    @Override
    public boolean scaleDeployment(DeployContext context) {
        String namespace = context.getNamespace().getName().getName();
        ResourceStrategy resourceStrategy = context.getAppEnv().getResourceStrategy();
        Integer replicas = resourceStrategy.getReplicas();
        try {
            AppsV1Api apiInstance = new AppsV1Api(apiClient);

            V1Scale scale = new V1Scale()
                    .spec(new V1ScaleSpec().replicas(replicas));
            apiInstance.replaceNamespacedDeploymentScale(context.getDeploymentName(), namespace, scale, null, null, null, null);
            log.info("Deployment scaled to replicas: " + replicas);
            return true;
        } catch (ApiException e) {
            log.error("Failed to scale Deployment.", e);
            return false;
        }
    }


    public boolean rollbackDeployment(DeployContext context) {
        String namespace = context.getNamespace().getName().getName();
        String deploymentName = context.getDeploymentName();
        AppsV1Api apiInstance = new AppsV1Api(apiClient);

//        try {
//            // 获取要回滚的 Deployment 的当前版本号
//            V1Deployment currentDeployment = apiInstance.readNamespacedDeployment(context.getDeploymentName(), namespace, null);
//            String currentRevision = currentDeployment.getMetadata().getAnnotations().get("deployment.kubernetes.io/revision");
//
//            // 创建新的 Deployment 对象，回滚到上一个版本
//            V1Deployment newDeployment = new V1Deployment()
//                    .metadata(new V1ObjectMeta().name(deploymentName))
//                    .spec(new V1DeploymentSpec()
//                            // 设置其他 Deployment 配置...
//                            .template(currentDeployment.getSpec().getTemplate())
//                    );
//
//            // 替换现有的 Deployment 对象
//            apiInstance.replaceNamespacedDeployment(deploymentName, namespace, newDeployment, null, null, null, null);
//            log.info("Deployment rolled back successfully.");
//            return true;
//        } catch (ApiException e) {
//            log.error("Failed to rollback Deployment.", e);
//            return false;
//        }
        return true;
    }


    @Override
    public boolean deleteDeployment(DeployContext context) {
        String namespace = context.getNamespace().getName().getName();
        // 删除 Deployment
        try {
            AppsV1Api apiInstance = new AppsV1Api(apiClient);
            apiInstance.deleteNamespacedDeployment(
                    context.getDeploymentName(), // Deployment 名称
                    namespace, // 命名空间
                    null, // 删除选项
                    null, // 删除时的预览
                    null, // 删除策略
                    null, // 处理选项
                    null,  // 查询参数
                    null
            );
            log.info("Deployment deleted successfully.");
            return true;
        } catch (ApiException e) {
            log.error("Failed to delete Deployment.", e);
            return false;
        }
    }

    @Override
    public boolean createService(DeployContext context) {
        AppName appName = context.getApp().getAppName();
        String namespace = context.getNamespace().getName().getName();

        CoreV1Api coreV1Api = new CoreV1Api(apiClient);
        try {
            V1ServiceSpec app = new V1ServiceSpec()
                    .selector(Collections.singletonMap("app", appName.getName()))
                    .type(context.getServiceType());
            if ("NodePort".equalsIgnoreCase(context.getServiceType())) {
                app.setPorts(Collections.singletonList(
                        new V1ServicePort().port(context.getServicePort())
                                .targetPort(new IntOrString(context.getContainerPort()))
                                .nodePort(context.getNodePort())
                                .protocol(context.getServiceProtocal())
                ));
            }
            if ("ClusterIP".equalsIgnoreCase(context.getServiceType())) {
                app.setPorts(Collections.singletonList(
                        new V1ServicePort().port(context.getServicePort())
                                .targetPort(new IntOrString(context.getContainerPort()))
                                .protocol(context.getServiceProtocal())
                ));
            }
            V1Service service = new V1Service()
                    .metadata(new V1ObjectMeta().name(context.getServiceName()))
                    .spec(app);
            coreV1Api.createNamespacedService(namespace, service, null, null, null, null);
            log.info("Service created successfully.");
            return true;
        } catch (ApiException e) {
            log.error("Failed to create Service.", e);
            return false;
        }
    }

    @Override
    public boolean updateService(DeployContext context) {
        String namespace = context.getNamespace().getName().getName();
        try {
            CoreV1Api coreV1Api = new CoreV1Api(apiClient);

            V1Service service = coreV1Api.readNamespacedService(context.getServiceName(), namespace, null);
            // 在这里执行更新 Service 的逻辑，可以修改 Service 的 selector、ports 等属性
            coreV1Api.replaceNamespacedService(context.getServiceName(), namespace, service, null, null, null, null);
            log.info("Service updated successfully.");
            return true;
        } catch (ApiException e) {
            log.error("Failed to update Service.", e);
            return false;
        }
    }

    @Override
    public boolean deleteService(DeployContext context) {
        String namespace = context.getNamespace().getName().getName();
        try {
            CoreV1Api coreV1Api = new CoreV1Api(apiClient);
            // 调用 Kubernetes API 删除 Service
            coreV1Api.deleteNamespacedService(
                    context.getServiceName(),
                    // Service 名称
                    namespace,
                    // 命名空间
                    null,
                    // 删除选项
                    null,
                    // 删除时的预览
                    null,
                    // 删除策略
                    null,
                    // 处理选项
                    null,
                    // 查询参数
                    new V1DeleteOptions()
                    // 删除选项
            );
            log.info("Service delete successfully.");
            return true;
        } catch (ApiException e) {
            log.error("Failed to delete Service.", e);
            return false;
        }
    }

    @Override
    public boolean createConfigMap(DeployContext context) {
        String namespace = context.getNamespace().getName().getName();
        AppName appName = context.getApp().getAppName();
        String envName = context.getAppEnv().getEnv().name().toLowerCase();
        String configMapName = String.format("%s-%s", appName.getName(), envName);
        Map<String, String> configMapPair = context.getConfigMap();
        try {
            CoreV1Api coreV1Api = new CoreV1Api(apiClient);
            // 创建 ConfigMap 对象
            V1ConfigMap configMap = new V1ConfigMap()
                    .apiVersion("v1")
                    .kind("ConfigMap")
                    .metadata(new V1ObjectMeta().name(configMapName))
                    .data(configMapPair);
            // 添加需要的键值对
            // 创建 ConfigMap
            coreV1Api.createNamespacedConfigMap(namespace, configMap,
                    null, null, null, null);
            log.info("ConfigMap create successfully.");
            return true;
        } catch (ApiException e) {
            log.error("Failed to create ConfigMap.", e);
            return false;
        }
    }


    @Override
    public boolean createOrUpdateConfigMap(DeployContext context) {
        String namespace = context.getNamespace().getName().getName();
        AppName appName = context.getApp().getAppName();
        String envName = context.getAppEnv().getEnv().name().toLowerCase();
        String configMapName = String.format("%s-%s", appName.getName(), envName);
        Map<String, String> configMapData = context.getAppEnv().getConfigMap();
        Map<String, String> updateConfigMapData = context.getConfigMap();

        try {
            CoreV1Api coreV1Api = new CoreV1Api(apiClient);
            V1ConfigMap configMap = new V1ConfigMap()
                    .apiVersion("v1")
                    .kind("ConfigMap")
                    .metadata(new V1ObjectMeta().name(configMapName))
                    .data(updateConfigMapData);

            // 尝试获取现有的 ConfigMap
            if (configMapData != null) {
                // 如果存在，则更新 ConfigMap
                // 检查数据是否已经相同，避免不必要的更新
                if (StringUtils.equals(JSON.toJSONString(configMapData), JSON.toJSONString(updateConfigMapData))) {
                    coreV1Api.replaceNamespacedConfigMap(configMapName, namespace, configMap, null, null, null, null);
                    log.info("ConfigMap {} updated successfully.", configMapName);
                } else {
                    log.info("ConfigMap {} already up-to-date.", configMapName);
                }
            } else {
                // 如果不存在，则创建 ConfigMap
                coreV1Api.createNamespacedConfigMap(namespace, configMap, null, null, null, null);
                log.info("ConfigMap {} created successfully.", configMapName);
            }
            return true;
        } catch (ApiException e) {
            // 处理其他类型的错误
            log.error("Error while creating or updating ConfigMap {}: {}", configMapName, e.getResponseBody(), e);
            return false;
        }
    }

    @Override
    public boolean updateConfigMap(DeployContext context) {
        String namespace = context.getNamespace().getName().getName();
        AppName appName = context.getApp().getAppName();
        String envName = context.getAppEnv().getEnv().name().toLowerCase();
        String configMapName = String.format("%s-%s", appName.getName(), envName);
        Map<String, String> configMapPair = context.getConfigMap();
        try {
            CoreV1Api coreV1Api = new CoreV1Api(apiClient);
            // 获取要更新的 ConfigMap
            V1ConfigMap existingConfigMap = coreV1Api.readNamespacedConfigMap(configMapName, namespace, null);
            // 修改 ConfigMap 的数据
            existingConfigMap.setData(configMapPair);
            // 更新 ConfigMap
            coreV1Api.replaceNamespacedConfigMap(configMapName, namespace, existingConfigMap, null, null, null, null);
            log.info("ConfigMap update successfully.");
            return true;
        } catch (ApiException e) {
            log.error("Failed to update ConfigMap.", e);
            return false;
        }
    }

    @Override
    public boolean createIngress(DeployContext context) {
        String namespace = context.getNamespace().getName().getName();
        List<String> domains = context.getAppEnv().getDomains();
        List<V1IngressRule> rules = new ArrayList<>();
        for (String domain : domains) {
            V1IngressRule http = new V1IngressRule()
                    .host(domain)  // 替换为你的域名
                    .http(new V1HTTPIngressRuleValue()
                            .addPathsItem(
                                    new V1HTTPIngressPath()
                                            .backend(
                                                    new V1IngressBackend()
                                                            .service(
                                                                    new V1IngressServiceBackend()
                                                                            .name(context.getServiceName())
                                                                            .port(new V1ServiceBackendPort()
                                                                                    .number(context.getServicePort())))
                                            ).path("/")
                            )

                    );
            rules.add(http);
        }

        // 添加 defaultBackend 配置
        V1IngressBackend defaultBackend = new V1IngressBackend()
                .service(new V1IngressServiceBackend()
                        .name(KubernetesConstants.DEFAULT_BACKEND_SERVICE)
                        // 假设您有一个方法或变量来提供默认服务名
                        .port(new V1ServiceBackendPort().number(KubernetesConstants.DEFAULT_BACKEND_SERVICE_PORT)));
        // 同样假设默认服务端口的提供方式

        // 确保至少有规则或defaultBackend
        if (rules.isEmpty()) {
            log.warn("No domain rules provided, using default backend.");
            return true;
        }

        V1IngressSpec ingressSpec = new V1IngressSpec()
                .rules(rules)
                .defaultBackend(defaultBackend);
        // 添加这一行来指定默认后端

        try {
            NetworkingV1Api networkingV1Api = new NetworkingV1Api(apiClient);
            // 创建 Ingress 对象
            V1Ingress ingress = new V1Ingress()
                    .apiVersion("networking.k8s.io/v1")
                    .kind("Ingress")
                    .metadata(new V1ObjectMeta().name(context.getIngressName()))
                    .spec(ingressSpec);

            // 调用 Kubernetes API 创建 Ingress
            networkingV1Api.createNamespacedIngress(namespace, ingress, null, null, null, null);
            log.info("Ingress created successfully.");
            return true;
        } catch (ApiException e) {
            log.error("Failed to create Ingress: " + e.getResponseBody());
            return false;
        }
    }

    @Override
    public boolean createOrUpdateIngress(DeployContext context) {
        String namespace = context.getNamespace().getName().getName();
        List<String> domains = context.getAppEnv().getDomains();
        List<V1IngressRule> rules = new ArrayList<>();
        for (String domain : domains) {
            V1IngressRule rule = new V1IngressRule()
                    .host(domain)
                    .http(new V1HTTPIngressRuleValue()
                            .addPathsItem(
                                    new V1HTTPIngressPath()
                                            .backend(
                                                    new V1IngressBackend()
                                                            .service(
                                                                    new V1IngressServiceBackend()
                                                                            .name(context.getServiceName())
                                                                            .port(new V1ServiceBackendPort()
                                                                                    .number(context.getServicePort())))
                                            ).path("/")
                                            .pathType("Prefix")
                            )
                    );
            rules.add(rule);
        }


        // 添加 defaultBackend 配置
        V1IngressBackend defaultBackend = new V1IngressBackend()
                .service(new V1IngressServiceBackend()
                        .name(KubernetesConstants.DEFAULT_BACKEND_SERVICE)
                        // 假设您有一个方法或变量来提供默认服务名
                        .port(new V1ServiceBackendPort().number(KubernetesConstants.DEFAULT_BACKEND_SERVICE_PORT)));
        // 同样假设默认服务端口的提供方式

        // 确保至少有规则或defaultBackend
        if (rules.isEmpty()) {
            log.warn("No domain rules provided, using default backend.");
            return true;
        }

        V1IngressSpec ingressSpec = new V1IngressSpec()
                .rules(rules)
                .defaultBackend(defaultBackend);
        // 添加这一行来指定默认后端

        try {
            NetworkingV1Api networkingV1Api = new NetworkingV1Api(apiClient);
            V1Ingress ingress = new V1Ingress()
                    .apiVersion("networking.k8s.io/v1")
                    .kind("Ingress")
                    .metadata(new V1ObjectMeta().name(context.getIngressName()))
                    .spec(ingressSpec);

            // 检查现有的 Ingress 是否存在
            boolean existingIngress = StringUtils.isNoneBlank(context.getAppEnv().getIngressName());
            if (existingIngress) {
                // 如果存在，则更新
                networkingV1Api.replaceNamespacedIngress(context.getIngressName(), namespace, ingress, null, null, null, null);
                log.info("Ingress {} updated successfully.", context.getIngressName());
            } else {
                // 如果不存在，则创建
                networkingV1Api.createNamespacedIngress(namespace, ingress, null, null, null, null);
                log.info("Ingress {} created successfully.", context.getIngressName());
            }
            return true;
        } catch (ApiException e) {
            log.error("Failed to create or update Ingress: {}", context.getIngressName(), e);
            return false;
        }
    }


    @Override
    public boolean deleteIngress(DeployContext context) {
        String namespace = context.getNamespace().getName().getName();
        try {
            NetworkingV1Api networkingV1Api = new NetworkingV1Api(apiClient);
            // 调用 Kubernetes API 删除 Ingress
            networkingV1Api.deleteNamespacedIngress(context.getIngressName(), namespace, null, null, null, null, null, null);
            log.info("Ingress deleted successfully.");
            return true;
        } catch (ApiException e) {
            log.error("Failed to delete Ingress: " + e.getResponseBody());
            return false;
        }
    }

    @Override
    public boolean createNameSpace(DeployContext context) {
        String namespaceValue = context.getNamespace().getName().getName();
        try {
            // 创建 V1Namespace 对象
            V1Namespace namespace = new V1Namespace()
                    .metadata(new V1ObjectMeta().name(namespaceValue));
            CoreV1Api coreV1Api = new CoreV1Api(apiClient);
            // 调用 Kubernetes API 创建 Namespace
            coreV1Api.createNamespace(namespace, null, null, null, null);
            log.info("Namespace created successfully.");
            return true;
        } catch (ApiException e) {
            log.error("Failed to delete Namespace: " + e.getResponseBody(), e);
            return false;
        }
    }


    @Override
    public List<V1Namespace> listNamespaces(DeployContext context) {
        try {
            CoreV1Api coreV1Api = new CoreV1Api(apiClient);
            List<V1Namespace> items = coreV1Api.listNamespace(null, null, null, null, null, null, null, null, null, null)
                    .getItems();
            return items;
        } catch (ApiException e) {
            log.error("Failed to delete Namespace: " + e.getResponseBody(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public V1Namespace getNamespace(DeployContext context) {
        try {
            String namespaceName = context.getNamespace().getName().getName();
            CoreV1Api coreV1Api = new CoreV1Api(apiClient);
            return coreV1Api.readNamespace(namespaceName, null);
        } catch (ApiException e) {
            log.error("Failed to get Namespace: " + e.getResponseBody(), e);
        }
        return null;
    }

    @Override
    public V1Namespace getNamespace(String namespaceName) {
        try {
            CoreV1Api coreV1Api = new CoreV1Api(apiClient);
            return coreV1Api.readNamespace(namespaceName, null);
        } catch (ApiException e) {
            log.error("Failed to delete Namespace: " + e.getResponseBody(), e);
        }
        return null;
    }


    private V1Deployment createBasicDeployment(DeployContext context) {
        AppName appName = context.getApp().getAppName();
        String healthCheck = context.getApp().getHealthCheck();
        Map<String, String> configMap = context.getAppEnv().getConfigMap();
        List<VolumeMount> volumeMounts = context.getApp().getVolumeMounts();
        String envId = context.getAppEnv().getEnvId();
        ResourceStrategy resourceStrategy = context.getAppEnv().getResourceStrategy();
        String configMapName = String.format("%s-%s", appName.getName(), envId);
        // 配置configMap
        List<V1EnvVar> configMapValues = new ArrayList<>();
        if (configMap != null && !configMap.isEmpty()) {
            for (Map.Entry<String, String> entry : configMap.entrySet()) {
                V1EnvVar envVar = new V1EnvVar()
                        .name(entry.getKey())
                        // 环境变量名称
                        .valueFrom(new V1EnvVarSource()
                                .configMapKeyRef(new V1ConfigMapKeySelector()
                                        .name(configMapName)
                                        .key(entry.getKey())));
                configMapValues.add(envVar);
            }
        }

        // 添加 SPRING_PROFILES_ACTIVE 环境变量
        V1EnvVar springProfilesActiveEnv = new V1EnvVar()
                .name("SPRING_PROFILES_ACTIVE")
                .value(context.getAppEnv().getEnv().name().toLowerCase());  // 直接设置环境变量的值
        configMapValues.add(springProfilesActiveEnv);


        List<V1Volume> dataVolumes = new ArrayList<>();
        List<V1VolumeMount> dataMounts = new ArrayList<>();
        if (volumeMounts != null && !volumeMounts.isEmpty()) {
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
        }


        // 创建一个简单的 Deployment 对象
        V1Container container = new V1Container()
                .name(appName.getName())
                .image(context.getDeploymentImage())
                .env(configMapValues)
                // .resources(requirements)
                .ports(Collections.singletonList(new V1ContainerPort().containerPort(context.getContainerPort())))
                // 存活性探针（Liveness Probe）
                // 存活性探针用于确定容器是否存活。如果存活性探针失败，Kubernetes 将尝试重新启动容器。
                .livenessProbe(
                        new V1Probe()
                                .httpGet(new V1HTTPGetAction()
                                        .path(healthCheck)
                                        // 替换为你的健康检查路径
                                        .port(new IntOrString(context.getContainerPort())))
                                .initialDelaySeconds(120)
                                // 初始延迟
                                .periodSeconds(3)
                                // 探测周期
                                .timeoutSeconds(1)
                                // 超时时间
                                .successThreshold(1)
                                // 成功阈值
                                .failureThreshold(3)
                        // 失败阈值
                )
                .volumeMounts(dataMounts);

        // 设置资源
        if (!StringUtils.isAllBlank(resourceStrategy.getCpu(), resourceStrategy.getMemory(), resourceStrategy.getMaxCpu(), resourceStrategy.getMaxMemory())) {
            Map<String, Quantity> request = new HashMap<>();
            request.put("cpu", new Quantity(resourceStrategy.getCpu()));
            request.put("memory", new Quantity(resourceStrategy.getMemory()));
            Map<String, Quantity> limit = new HashMap<>();
            limit.put("cpu", new Quantity(resourceStrategy.getMaxCpu()));
            limit.put("memory", new Quantity(resourceStrategy.getMaxMemory()));
            V1ResourceRequirements requirements = new V1ResourceRequirements()
                    .limits(limit)
                    .requests(request);
            container.resources(requirements);
        }

        return new V1Deployment()
                .metadata(new V1ObjectMeta().name(context.getDeploymentName()))
                .spec(new V1DeploymentSpec()
                        .replicas(resourceStrategy.getReplicas())
                        .selector(new V1LabelSelector().matchLabels(Collections.singletonMap("app", appName.getName())))
                        .template(new V1PodTemplateSpec()
                                .metadata(new V1ObjectMeta().labels(Collections.singletonMap("app", appName.getName())))
                                // 创建一个新的 Pod 规格对象 (V1PodSpec)，该对象包含了定义 Pod 的各种配置信息，如容器、卷挂载、标签选择器等。
                                .spec(new V1PodSpec()
                                        .imagePullSecrets(Lists.of(
                                                new V1LocalObjectReference().name("image-pull-secret")
                                        ))
                                        .containers(Collections.singletonList(container)).volumes(dataVolumes)
                                )
                        )
                );
    }

    @Override
    public boolean updateDeploymentResources(DeployContext context) {
        String namespace = context.getNamespace().getName().getName();
        String deploymentName = context.getDeploymentName();
        ResourceStrategy resourceStrategy = context.getAppEnv().getResourceStrategy();

        try {
            AppsV1Api apiInstance = new AppsV1Api(apiClient);
            // 获取现有的 Deployment
            V1Deployment existingDeployment = apiInstance.readNamespacedDeployment(deploymentName, namespace, null);

            // 更新容器的资源配置
            V1Container container = existingDeployment.getSpec().getTemplate().getSpec().getContainers().get(0);

            Map<String, Quantity> request = new HashMap<>();
            request.put("cpu", new Quantity(resourceStrategy.getCpu()));
            request.put("memory", new Quantity(resourceStrategy.getMemory()));

            Map<String, Quantity> limit = new HashMap<>();
            limit.put("cpu", new Quantity(resourceStrategy.getMaxCpu()));
            limit.put("memory", new Quantity(resourceStrategy.getMaxMemory()));

            V1ResourceRequirements requirements = new V1ResourceRequirements()
                    .limits(limit)
                    .requests(request);

            container.setResources(requirements);

            // 提交更新后的 Deployment
            apiInstance.replaceNamespacedDeployment(deploymentName, namespace, existingDeployment, null, null, null, null);
            log.info("Deployment {} resources updated successfully.", deploymentName);
            return true;
        } catch (ApiException e) {
            log.error("Failed to update Deployment {} resources. {}", deploymentName, e.getResponseBody(), e);
            return false;
        }
    }

    @Override
    public boolean updateDeploymentEnvVars(DeployContext context) {
        String namespace = context.getNamespace().getName().getName();
        String deploymentName = context.getDeploymentName();
        Map<String, String> envVars = context.getAppEnv().getEnvVars();
        try {
            AppsV1Api apiInstance = new AppsV1Api(apiClient);
            // 获取现有的 Deployment
            V1Deployment existingDeployment = apiInstance.readNamespacedDeployment(deploymentName, namespace, null);
            // 更新容器的环境变量配置
            V1Container container = existingDeployment.getSpec().getTemplate().getSpec().getContainers().get(0);
            // 清空现有的环境变量
            container.setEnv(new ArrayList<>());

            // 根据 envVars 设置新的环境变量
            for (Map.Entry<String, String> entry : envVars.entrySet()) {
                V1EnvVar envVar = new V1EnvVar()
                        .name(entry.getKey())
                        .value(entry.getValue());
                container.addEnvItem(envVar);
            }

            // 提交更新后的 Deployment
            apiInstance.replaceNamespacedDeployment(deploymentName, namespace, existingDeployment, null, null, null, null);
            log.info("Deployment {} environment variables updated successfully.", deploymentName);
            return true;
        } catch (ApiException e) {
            log.error("Failed to update Deployment {} environment variables. {}", deploymentName, e.getResponseBody(), e);
            return false;
        }
    }
}
