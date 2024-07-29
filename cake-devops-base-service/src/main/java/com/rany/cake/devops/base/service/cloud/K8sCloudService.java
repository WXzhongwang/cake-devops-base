package com.rany.cake.devops.base.service.cloud;

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
                System.out.println("Pod Name: " + Objects.requireNonNull(pod.getMetadata()).getName());
                System.out.println("Namespace: " + pod.getMetadata().getNamespace());
                System.out.println("Status: " + Objects.requireNonNull(pod.getStatus()).getPhase());
                System.out.println("--------------------------------");
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
            log.error("Failed to create Deployment.", e);
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
        try {
            CoreV1Api coreV1Api = new CoreV1Api(apiClient);
            V1Service service = new V1Service()
                    .metadata(new V1ObjectMeta().name(context.getServiceName()))
                    .spec(new V1ServiceSpec()
                            .selector(Collections.singletonMap("app", appName.getName()))
                            .ports(Collections.singletonList(
                                    new V1ServicePort().port(context.getServicePort())
                                            .targetPort(new IntOrString(context.getContainerPort()))
                            ))
                            .type("ClusterIP"));
            // context.getServicePort() 获取了服务的端口号。这个端口是 Service 对外提供服务的端口，即客户端通过这个端口访问服务。
            // .targetPort(new IntOrString(context.getContainerPort()))：设置了目标端口，即 Service 转发到 Pod 内容器的端口。context.getContainerPort() 获取了容器的端口号。这个端口是 Pod 内部容器提供服务的端口。
            // 服务的类型:ClusterIP，表示服务只能在集群内部访问。其他可能的类型包括 NodePort、LoadBalancer 等。
            // 服务类型，可以根据需求调整
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
                    context.getServiceName(),   // Service 名称
                    namespace,      // 命名空间
                    null,                        // 删除选项
                    null,                        // 删除时的预览
                    null,                        // 删除策略
                    null,                        // 处理选项
                    null,                        // 查询参数
                    new V1DeleteOptions()        // 删除选项
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
        String envName = context.getAppEnv().getEnvName();
        String configMapName = String.format("%s-%s", appName, envName);
        Map<String, String> configMapPair = context.getAppEnv().getConfigMap();
        try {
            CoreV1Api coreV1Api = new CoreV1Api(apiClient);
            // 创建 ConfigMap 对象
            V1ConfigMap configMap = new V1ConfigMap()
                    .apiVersion("v1")
                    .kind("ConfigMap")
                    .metadata(new V1ObjectMeta().name(configMapName))
                    .data(configMapPair);   // 添加需要的键值对
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
    public boolean updateConfigMap(DeployContext context) {
        String namespace = context.getNamespace().getName().getName();
        AppName appName = context.getApp().getAppName();
        String envName = context.getAppEnv().getEnvName();
        String configMapName = String.format("%s-%s", appName, envName);
        Map<String, String> configMapPair = context.getAppEnv().getConfigMap();
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
        try {
            NetworkingV1Api networkingV1Api = new NetworkingV1Api(apiClient);
            // 创建 Ingress 对象
            V1Ingress ingress = new V1Ingress()
                    .apiVersion("networking.k8s.io/v1")
                    .kind("Ingress")
                    .metadata(new V1ObjectMeta().name(context.getIngressName()))
                    .spec(new V1IngressSpec().rules(rules));

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
            log.error("Failed to delete Namespace: " + e.getResponseBody(), e);
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
        String envName = context.getAppEnv().getEnvName();
        ResourceStrategy resourceStrategy = context.getAppEnv().getResourceStrategy();
        String configMapName = String.format("%s-%s", appName.getName(), envName);
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
