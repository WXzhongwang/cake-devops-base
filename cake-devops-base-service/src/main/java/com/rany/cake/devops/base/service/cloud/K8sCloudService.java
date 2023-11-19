package com.rany.cake.devops.base.service.cloud;

import com.rany.cake.devops.base.domain.type.AppName;
import com.rany.cake.devops.base.service.context.DeployContext;
import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * K8sCloudService
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/20 20:51
 * @email 18668485565163.com
 */
@Slf4j
public class K8sCloudService extends BaseCloudService {

    public K8sCloudService(String connectString, String token) {
        build(connectString, token);
    }

    @Override
    public boolean createDeployment(DeployContext context) {
        String namespace = context.getNamespace().getName().getName();
        // 创建 Deployment
        V1Deployment deployment = createSampleDeployment(context);
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
}
