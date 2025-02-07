package com.rany.cake.devops.base.service.integration.cloud;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.rany.cake.devops.base.domain.valueobject.ResourceStrategy;
import com.rany.cake.devops.base.domain.valueobject.VolumeMount;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.integration.cloud.dto.*;
import com.rany.cake.devops.base.service.utils.KubernetesUtils;
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

    public static final String FIX_LABEL = "k8s-app";

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
    public boolean createDeployment(DeployContext context, CreateDeploymentCmd createDeploymentCmd) {
        String namespace = createDeploymentCmd.getNamespace();
        // 创建 Deployment
        V1Deployment deployment = createBasicDeployment(createDeploymentCmd);
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
    public boolean createOrUpdateDeployment(DeployContext context, CreateDeploymentCmd createDeploymentCmd) {
        String deploymentName = createDeploymentCmd.getDeploymentName();
        String namespace = createDeploymentCmd.getNamespace();
        // 假设这里有获取Deployment名称的方法
        // 创建 Deployment
        V1Deployment deployment = createBasicDeployment(createDeploymentCmd);

        AppsV1Api apiInstance = new AppsV1Api(apiClient);
        try {
            V1Deployment existingDeployment = apiInstance.readNamespacedDeployment(deploymentName, namespace, null);
            if (existingDeployment != null) {
                // 如果存在，则更新
                apiInstance.replaceNamespacedDeployment(deploymentName, namespace, deployment, null, null, null, null);
                log.info("Deployment {} updated successfully.", deploymentName);
            }
            return true;
        } catch (ApiException e) {
            // 检查异常是否是因为 Deployment 不存在
            if (e.getCode() == 404) {
                log.warn("Deployment {} not found. Creating new Deployment.", deploymentName);
                try {
                    apiInstance.createNamespacedDeployment(namespace, deployment, null, null, null, null);
                    log.info("Deployment {} created successfully.", deploymentName);
                    return true;
                } catch (ApiException createException) {
                    log.error("Failed to create Deployment {}. {}", deploymentName, createException.getResponseBody(), createException);
                    return false;
                }
            } else {
                log.error("Failed to create or update Deployment {}. {}", deploymentName, e.getResponseBody(), e);
                return false;
            }
        }
    }


    /**
     * 获取与指定 Deployment 关联的所有 Pods。
     *
     * @param context 部署上下文
     * @return Pod 列表
     */
    @Override
    public List<PodInfoDTO> getPodsForDeployment(DeployContext context, ListPodCmd listPodCmd) {
        String namespace = listPodCmd.getNamespace();
        String deploymentName = listPodCmd.getDeploymentName();
        try {
            CoreV1Api apiInstance = new CoreV1Api(apiClient);
            // 使用 Deployment 的 selector 来过滤 Pods
            String labelSelector = "app=" + deploymentName; // 使用标准标签
            V1PodList podList = apiInstance.listNamespacedPod(namespace, null, null, null,
                    null, labelSelector, null, null, null, null, false);
            List<V1Pod> items = podList.getItems();
            List<PodInfoDTO> podInfos = KubernetesUtils.convertPodsToDTOs(items);
            for (PodInfoDTO podInfo : podInfos) {
                System.out.println("Name: " + podInfo.getName());
                System.out.println("Namespace: " + podInfo.getNamespace());
                System.out.println("Pod IP: " + podInfo.getPodIp());
                System.out.println("Phase: " + podInfo.getPhase());
                System.out.println("Node Name: " + podInfo.getNodeName());
                System.out.println("Start Time: " + podInfo.getStartTime()); // 输出格式化后的时间字符串
                System.out.println("Is Ready: " + podInfo.isReady());
            }
            return podInfos;
        } catch (ApiException e) {
            log.error("Failed to list Pods for Deployment {}. {}", deploymentName, e.getResponseBody(), e);
            return null;
        }
    }

    @Override
    public boolean scaleDeployment(DeployContext context, ScaleDeploymentCmd scaleDeploymentCmd) {
        String namespace = scaleDeploymentCmd.getNamespace();
        Integer replicas = scaleDeploymentCmd.getReplicas();
        String deploymentName = scaleDeploymentCmd.getDeploymentName();
        try {
            AppsV1Api apiInstance = new AppsV1Api(apiClient);
            // 获取 V1Deployment
            V1Deployment deploy = apiInstance.readNamespacedDeployment(deploymentName, namespace, null);
            try {
                V1DeploymentSpec newSpec = deploy.getSpec().replicas(replicas);
                V1Deployment newDeploy = deploy.spec(newSpec);
                apiInstance.replaceNamespacedDeployment(deploymentName, namespace, newDeploy, null, null, null, null);
            } catch (ApiException ex) {
                log.warn("Scale the pod failed for Deployment:" + deploymentName, ex);
            }
            return true;
        } catch (ApiException e) {
            log.error("Failed to scale Deployment.", e);
            return false;
        }
    }


    @Override
    public boolean rollbackDeployment(DeployContext context) {
        String namespace = context.getNamespace().getName().getName();
        return true;
    }


    @Override
    public boolean deleteDeployment(DeployContext context, DeleteDeploymentCmd deleteDeploymentCmd) {
        String namespace = deleteDeploymentCmd.getNamespace();
        // 删除 Deployment
        try {
            AppsV1Api apiInstance = new AppsV1Api(apiClient);
            apiInstance.deleteNamespacedDeployment(
                    deleteDeploymentCmd.getDeploymentName(), // Deployment 名称
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
    public boolean createService(DeployContext context, CreateServiceCmd createServiceCmd) {
        String appName = createServiceCmd.getAppName();

        CoreV1Api coreV1Api = new CoreV1Api(apiClient);
        try {
            V1ServiceSpec app = new V1ServiceSpec()
                    .selector(Collections.singletonMap("app", appName))
                    .type(createServiceCmd.getServiceType());
            if ("NodePort".equalsIgnoreCase(createServiceCmd.getServiceType())) {
                app.setPorts(Collections.singletonList(
                        new V1ServicePort().port(createServiceCmd.getServicePort())
                                .targetPort(new IntOrString(createServiceCmd.getContainerPort()))
                                .nodePort(createServiceCmd.getNodePort())
                                .protocol(createServiceCmd.getServiceProtocol())
                ));
            }
            if ("ClusterIP".equalsIgnoreCase(createServiceCmd.getServiceType())) {
                app.setPorts(Collections.singletonList(
                        new V1ServicePort().port(createServiceCmd.getServicePort())
                                .targetPort(new IntOrString(createServiceCmd.getContainerPort()))
                                .protocol(createServiceCmd.getServiceProtocol())
                ));
            }
            V1Service service = new V1Service()
                    .metadata(new V1ObjectMeta().name(createServiceCmd.getServiceName()))
                    .spec(app);
            coreV1Api.createNamespacedService(createServiceCmd.getNamespace(), service, null, null, null, null);
            log.info("Service created successfully.");
            return true;
        } catch (ApiException e) {
            log.error("Failed to create Service.", e);
            return false;
        }
    }

    @Override
    public boolean updateService(DeployContext context, ModifyServiceCmd modifyServiceCmd) {
        String namespace = modifyServiceCmd.getNamespace();
        try {
            CoreV1Api coreV1Api = new CoreV1Api(apiClient);

            V1Service service = coreV1Api.readNamespacedService(modifyServiceCmd.getServiceName(), namespace, null);
            // 在这里执行更新 Service 的逻辑，可以修改 Service 的 selector、ports 等属性
            if ("NodePort".equalsIgnoreCase(modifyServiceCmd.getServiceType())) {
                service.getSpec().setPorts(Collections.singletonList(
                        new V1ServicePort().port(modifyServiceCmd.getServicePort())
                                .targetPort(new IntOrString(modifyServiceCmd.getContainerPort()))
                                .nodePort(modifyServiceCmd.getNodePort())
                                .protocol(modifyServiceCmd.getServiceProtocol())
                ));
            }
            if ("ClusterIP".equalsIgnoreCase(modifyServiceCmd.getServiceType())) {
                service.getSpec().setPorts(Collections.singletonList(
                        new V1ServicePort().port(modifyServiceCmd.getServicePort())
                                .targetPort(new IntOrString(modifyServiceCmd.getContainerPort()))
                                .protocol(modifyServiceCmd.getServiceProtocol())
                ));
            }
            coreV1Api.replaceNamespacedService(modifyServiceCmd.getServiceName(), namespace, service, null, null, null, null);
            log.info("Service updated successfully.");
            return true;
        } catch (ApiException e) {
            log.error("Failed to update Service.", e);
            return false;
        }
    }

    @Override
    public boolean deleteService(DeployContext context, DeleteServiceCmd deleteServiceCmd) {
        try {
            CoreV1Api coreV1Api = new CoreV1Api(apiClient);
            // 调用 Kubernetes API 删除 Service
            coreV1Api.deleteNamespacedService(
                    deleteServiceCmd.getServiceName(),
                    // Service 名称
                    deleteServiceCmd.getNamespace(),
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
    public boolean createConfigMap(DeployContext context, CreateConfigMapCmd createConfigMapCmd) {
        String namespace = createConfigMapCmd.getNamespace();
        String appName = createConfigMapCmd.getAppName();
        String configMapName = "config-map-" + appName;
        Map<String, String> configMapPair = createConfigMapCmd.getConfigMap();

        V1ObjectMeta meta = new V1ObjectMeta();
        meta.setName(configMapName);
        Map<String, String> labels = Maps.newHashMapWithExpectedSize(1);
        labels.put(FIX_LABEL, appName);
        meta.setLabels(labels);


        try {
            CoreV1Api coreV1Api = new CoreV1Api(apiClient);
            // 创建 ConfigMap 对象
            V1ConfigMap configMap = new V1ConfigMap()
                    .apiVersion("v1")
                    .kind("ConfigMap")
                    .metadata(meta)
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
    public boolean createOrUpdateConfigMap(DeployContext context, UpdateConfigMapCmd updateConfigMapCmd) {
        String namespace = updateConfigMapCmd.getNamespace();
        String appName = updateConfigMapCmd.getAppName();
        String configMapName = "config-map-" + appName;
        Map<String, String> currentConfigMapData = updateConfigMapCmd.getCurrentConfigMap();
        Map<String, String> updateConfigMapData = updateConfigMapCmd.getConfigMap();

        V1ObjectMeta meta = new V1ObjectMeta();
        meta.setName(configMapName);
        Map<String, String> labels = Maps.newHashMapWithExpectedSize(1);
        labels.put(FIX_LABEL, appName);
        meta.setLabels(labels);


        CoreV1Api coreV1Api = new CoreV1Api(apiClient);
        V1ConfigMap configMap = new V1ConfigMap()
                .apiVersion("v1")
                .kind("ConfigMap")
                .metadata(meta)
                .data(updateConfigMapData);

        try {
            V1ConfigMap v1ConfigMap = coreV1Api.readNamespacedConfigMap(configMapName, namespace, null);
            Map<String, String> data = v1ConfigMap.getData();
            log.info("Current configMap data:{}", JSON.toJSONString(data));

            if (!KubernetesUtils.equalMaps2(data, currentConfigMapData)) {
                log.warn("Current inconsistent configMap data:{}", JSON.toJSONString(data));
            }

            if (!KubernetesUtils.equalMaps2(data, updateConfigMapData)) {
                coreV1Api.replaceNamespacedConfigMap(configMapName, namespace, configMap, null, null, null, null);
                log.info("ConfigMap {} updated successfully.", appName);
            }
            return true;
        } catch (ApiException e) {
            // 处理其他类型的错误
            if (e.getCode() == 404) {
                log.warn("ConfigMap {} not found. Creating new ConfigMap.", configMapName);
                try {
                    // 如果不存在，则创建 ConfigMap
                    coreV1Api.createNamespacedConfigMap(namespace, configMap, null, null, null, null);
                    log.info("ConfigMap {} created successfully.", appName);
                    return true;
                } catch (ApiException createException) {
                    log.error("Failed to create ConfigMap {}. {}", configMapName, createException.getResponseBody(), createException);
                    return false;
                }
            }
            log.error("Error while creating or updating ConfigMap {}: {}", appName, e.getResponseBody(), e);
            return false;
        }
    }

    @Override
    public boolean updateConfigMap(DeployContext context, UpdateConfigMapCmd updateConfigMapCmd) {
        String namespace = updateConfigMapCmd.getNamespace();
        String appName = updateConfigMapCmd.getAppName();
        String configMapName = "config-map-" + appName;
        Map<String, String> configMapPair = updateConfigMapCmd.getConfigMap();

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
    public boolean createSecret(DeployContext context, CreateSecretCmd createSecretCmd) {
        String namespace = createSecretCmd.getNamespace();
        String appName = createSecretCmd.getAppName();
        String secretName = "secret-map-" + appName;
        Map<String, byte[]> secretData = createSecretCmd.getSecretData();
        V1ObjectMeta meta = new V1ObjectMeta();
        meta.setName(secretName);
        Map<String, String> labels = Maps.newHashMapWithExpectedSize(1);
        labels.put(FIX_LABEL, appName);
        meta.setLabels(labels);


        try {
            CoreV1Api coreV1Api = new CoreV1Api(apiClient);
            // 创建 Secret 对象
            V1Secret secret = new V1Secret()
                    .apiVersion("v1")
                    .kind("Secret")
                    .metadata(meta)
                    .type("Opaque")
                    .data(secretData);
            // 创建 Secret
            coreV1Api.createNamespacedSecret(namespace, secret, null, null, null, null);
            log.info("Secret created successfully.");
            return true;
        } catch (ApiException e) {
            log.error("Failed to create Secret.", e);
            return false;
        }
    }

    @Override
    public boolean updateSecret(DeployContext context, UpdateSecretCmd updateSecretCmd) {
        String namespace = updateSecretCmd.getNamespace();
        String appName = updateSecretCmd.getAppName();
        String secretName = "secret-map-" + appName;
        Map<String, byte[]> secretData = updateSecretCmd.getSecretMap();

        try {
            CoreV1Api coreV1Api = new CoreV1Api(apiClient);
            // 获取要更新的 Secret
            V1Secret existingSecret = coreV1Api.readNamespacedSecret(secretName, namespace, null);
            // 修改 Secret 的数据
            existingSecret.setData(secretData);
            // 更新 Secret
            coreV1Api.replaceNamespacedSecret(secretName, namespace, existingSecret, null, null, null, null);
            log.info("Secret update successfully.");
            return true;
        } catch (ApiException e) {
            log.error("Failed to update Secret.", e);
            return false;
        }
    }

    @Override
    public boolean createOrUpdateSecret(DeployContext context, UpdateSecretCmd updateSecretCmd) {
        String namespace = updateSecretCmd.getNamespace();
        String appName = updateSecretCmd.getAppName();
        String secretName = "secret-map-" + appName;
        Map<String, byte[]> currentSecretData = updateSecretCmd.getCurrentSecretMap();
        Map<String, byte[]> updateSecretData = updateSecretCmd.getSecretMap();

        CoreV1Api coreV1Api = new CoreV1Api(apiClient);
        V1Secret secret = new V1Secret()
                .apiVersion("v1")
                .kind("Secret")
                .metadata(new V1ObjectMeta().name(secretName))
                .type("Opaque")
                .data(updateSecretData);

        try {
            V1Secret v1Secret = coreV1Api.readNamespacedSecret(secretName, namespace, null);
            Map<String, byte[]> data = v1Secret.getData();
            log.info("Current secret data:{}", JSON.toJSONString(data));

            if (!KubernetesUtils.equalMaps(data, currentSecretData)) {
                log.warn("Current inconsistent secret data:{}", JSON.toJSONString(data));
            }


            if (!KubernetesUtils.equalMaps(data, updateSecretData)) {
                coreV1Api.replaceNamespacedSecret(secretName, namespace, secret, null, null, null, null);
                log.info("Secret {} updated successfully.", appName);
            }
            return true;
        } catch (ApiException e) {
            // 处理其他类型的错误
            if (e.getCode() == 404) {
                log.warn("Secret {} not found. Creating new ConfigMap.", secretName);
                try {
                    // 如果不存在，则创建 ConfigMap
                    coreV1Api.createNamespacedSecret(namespace, secret, null, null, null, null);
                    log.info("Secret {} created successfully.", appName);
                    return true;
                } catch (ApiException createException) {
                    log.error("Failed to create Secret {}. {}", secretName, createException.getResponseBody(), createException);
                    return false;
                }
            }
            log.error("Error while creating or updating Secret {}: {}", secretName, e.getResponseBody(), e);
            return false;
        }
    }

    @Override
    public boolean createIngress(DeployContext context, CreateIngressCmd createIngressCmd) {
        String namespace = createIngressCmd.getNamespace();
        List<V1IngressRule> rules = new ArrayList<>();
        for (String domain : createIngressCmd.getDomains()) {
            V1IngressRule http = new V1IngressRule()
                    .host(domain)  // 替换为你的域名
                    .http(new V1HTTPIngressRuleValue()
                            .addPathsItem(
                                    new V1HTTPIngressPath()
                                            .backend(
                                                    new V1IngressBackend()
                                                            .service(
                                                                    new V1IngressServiceBackend()
                                                                            .name(createIngressCmd.getServiceName())
                                                                            .port(new V1ServiceBackendPort()
                                                                                    .number(createIngressCmd.getServicePort())))
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
                    .metadata(new V1ObjectMeta().name(createIngressCmd.getIngressName()))
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
    public boolean createOrUpdateIngress(DeployContext context, CreateIngressCmd createIngressCmd) {
        String namespace = createIngressCmd.getNamespace();
        List<String> domains = createIngressCmd.getDomains();
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
                                                                            .name(createIngressCmd.getServiceName())
                                                                            .port(new V1ServiceBackendPort()
                                                                                    .number(createIngressCmd.getServicePort())))
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
                    .metadata(new V1ObjectMeta().name(createIngressCmd.getIngressName()))
                    .spec(ingressSpec);

            // 检查现有的 Ingress 是否存在
            V1Ingress v1Ingress = networkingV1Api.readNamespacedIngress(createIngressCmd.getIngressName(), namespace, null);
            if (v1Ingress != null) {
                // 如果存在，则更新
                networkingV1Api.replaceNamespacedIngress(createIngressCmd.getIngressName(), namespace, ingress, null, null, null, null);
                log.info("Ingress {} updated successfully.", createIngressCmd.getIngressName());
            } else {
                // 如果不存在，则创建
                networkingV1Api.createNamespacedIngress(namespace, ingress, null, null, null, null);
                log.info("Ingress {} created successfully.", createIngressCmd.getIngressName());
            }
            return true;
        } catch (ApiException e) {
            log.error("Failed to create or update Ingress: {}", createIngressCmd.getIngressName(), e);
            return false;
        }
    }


    @Override
    public boolean deleteIngress(DeployContext context, DeleteIngressCmd deleteIngressCmd) {
        String namespace = deleteIngressCmd.getNamespace();
        try {
            NetworkingV1Api networkingV1Api = new NetworkingV1Api(apiClient);
            // 调用 Kubernetes API 删除 Ingress
            networkingV1Api.deleteNamespacedIngress(deleteIngressCmd.getIngressName(), namespace, null, null, null, null, null, null);
            log.info("Ingress deleted successfully.");
            return true;
        } catch (ApiException e) {
            log.error("Failed to delete Ingress: " + e.getResponseBody());
            return false;
        }
    }

    @Override
    public boolean createNameSpace(DeployContext context, CreateNameSpaceCmd createNameSpaceCmd) {
        String namespaceValue = createNameSpaceCmd.getNamespace();
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
    public V1Namespace getNamespace(String namespaceName) {
        try {
            CoreV1Api coreV1Api = new CoreV1Api(apiClient);
            return coreV1Api.readNamespace(namespaceName, null);
        } catch (ApiException e) {
            log.error("Failed to delete Namespace: " + e.getResponseBody(), e);
        }
        return null;
    }


    private V1Deployment createBasicDeployment(CreateDeploymentCmd createDeploymentCmd) {
        String appName = createDeploymentCmd.getAppName();
        String healthCheck = createDeploymentCmd.getHealthCheck();
        Map<String, String> envVars = createDeploymentCmd.getEnvVars();
        List<VolumeMount> volumeMounts = createDeploymentCmd.getVolumeMounts();
        ResourceStrategy resourceStrategy = createDeploymentCmd.getResourceStrategy();
        // 配置configMap
        List<V1EnvVar> envValues = new ArrayList<>();
        if (envVars != null && !envVars.isEmpty()) {
            for (Map.Entry<String, String> entry : envVars.entrySet()) {
                V1EnvVar envVar = new V1EnvVar()
                        .name(entry.getKey())
                        // 环境变量名称
                        .value(entry.getValue());
                envValues.add(envVar);
            }
        }


        List<V1Volume> dataVolumes = new ArrayList<>();
        List<V1VolumeMount> dataMounts = new ArrayList<>();
        // 处理 hostPath 卷
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

        // 处理 ConfigMap 卷
        String configMapName = "config-map-" + appName;
        if (StringUtils.isNotBlank(configMapName)) {
            V1Volume configVolume = new V1Volume()
                    .name("config-volume")
                    .configMap(new V1ConfigMapVolumeSource().name(configMapName));
            dataVolumes.add(configVolume);

            V1VolumeMount configMount = new V1VolumeMount()
                    .name("config-volume")
                    .mountPath("/etc/cake/");
            dataMounts.add(configMount);
        }

        // 处理 Secret 卷
        String secretName = "secret-map-" + appName;
        if (StringUtils.isNotBlank(secretName)) {
            V1Volume secretVolume = new V1Volume()
                    .name("secret-volume")
                    .secret(new V1SecretVolumeSource().secretName(secretName));
            dataVolumes.add(secretVolume);

            V1VolumeMount secretMount = new V1VolumeMount()
                    .name("secret-volume")
                    .mountPath("/etc/cake-secret/");
            dataMounts.add(secretMount);
        }


        // 创建一个简单的 Deployment 对象
        V1Container container = new V1Container()
                .name(appName)
                .image(createDeploymentCmd.getDeploymentImage())
                .env(envValues)
                .ports(Collections.singletonList(new V1ContainerPort().containerPort(createDeploymentCmd.getContainerPort())))
                // 存活性探针（Liveness Probe）
                // 存活性探针用于确定容器是否存活。如果存活性探针失败，Kubernetes 将尝试重新启动容器。
                .livenessProbe(
                        new V1Probe()
                                .httpGet(new V1HTTPGetAction()
                                        .path(healthCheck)
                                        // 替换为你的健康检查路径
                                        .port(new IntOrString(createDeploymentCmd.getContainerPort())))
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
                .metadata(new V1ObjectMeta().name(createDeploymentCmd.getDeploymentName()))
                .spec(new V1DeploymentSpec()
                        .replicas(resourceStrategy.getReplicas())
                        .selector(new V1LabelSelector().matchLabels(Collections.singletonMap("app", appName)))
                        .template(new V1PodTemplateSpec()
                                .metadata(new V1ObjectMeta().labels(Collections.singletonMap("app", appName)))
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
    public boolean updateDeploymentResources(DeployContext context, UpdateResourceCmd updateResourceCmd) {
        String namespace = updateResourceCmd.getNamespace();
        String deploymentName = updateResourceCmd.getDeploymentName();
        ResourceStrategy resourceStrategy = updateResourceCmd.getResourceStrategy();

        try {
            AppsV1Api apiInstance = new AppsV1Api(apiClient);
            // 获取现有的 Deployment
            V1Deployment existingDeployment = apiInstance.readNamespacedDeployment(deploymentName, namespace, null);

            // 更新容器的资源配置
            List<V1Container> containers = existingDeployment.getSpec().getTemplate().getSpec().getContainers();
            if (containers != null && !containers.isEmpty()) {
                V1Container container = containers.get(0);

                V1ResourceRequirements requirements = new V1ResourceRequirements();
                if (!StringUtils.isAnyBlank(resourceStrategy.getCpu(), resourceStrategy.getMemory())) {
                    Map<String, Quantity> request = new HashMap<>();
                    request.put("cpu", new Quantity(resourceStrategy.getCpu()));
                    request.put("memory", new Quantity(resourceStrategy.getMemory()));
                    requirements.setRequests(request);
                }
                if (!StringUtils.isAnyBlank(resourceStrategy.getMaxCpu(), resourceStrategy.getMaxMemory())) {
                    Map<String, Quantity> limit = new HashMap<>();
                    limit.put("cpu", new Quantity(resourceStrategy.getMaxCpu()));
                    limit.put("memory", new Quantity(resourceStrategy.getMaxMemory()));
                    requirements.setLimits(limit);
                }

                container.setResources(requirements);
            }

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
    public boolean updateDeploymentEnvVars(DeployContext context, UpdateEnvVarsCmd updateEnvVarsCmd) {
        String namespace = updateEnvVarsCmd.getNamespace();
        String deploymentName = updateEnvVarsCmd.getDeploymentName();
        Map<String, String> envVars = updateEnvVarsCmd.getEnvVars();
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
