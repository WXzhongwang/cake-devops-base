package com.rany.cake.devops.base.service.integration.cloud;

import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.integration.cloud.dto.*;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.util.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;

/**
 * BaseCloudService
 *
 * @author zhongshengwang
 * @description BaseCloudService
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
            //this.apiClient = ClientBuilder.cluster().build();
//            // 指定 kubeconfig 文件路径
//            String kubeConfigPath = "/Users/yuanjinxiu/.kube/config";
//
//            // 从 kubeconfig 文件中加载配置
//            KubeConfig kubeConfig = KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath));
//            this.apiClient = ClientBuilder.kubeconfig(kubeConfig)
//                    .setVerifyingSsl(false)
//                    .build();


//            this.apiClient = ClientBuilder.kubeconfig(kubeConfig)
//                    .setVerifyingSsl(false)
//                    //.setCertificateAuthority()
//                    //.setBasePath("http://kubernetes.docker.internal:6443")
//                    .build();
//            this.apiClient = Config.fromConfig(new InputStreamReader(Files.newInputStream(Paths.get("/Users/yuanjinxiu/.kube/config"))))
//                    .setBasePath("https://kubernetes.docker.internal:6443")
//                    .setVerifyingSsl(false);

            this.apiClient = Config.defaultClient();
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
     * 测试连接
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean testConnection(DeployContext context);

    /**
     * createDeployment
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean createDeployment(DeployContext context, CreateDeploymentCmd createDeploymentCmd);

    /**
     * createOrUpdateDeployment
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean createOrUpdateDeployment(DeployContext context, CreateDeploymentCmd createDeploymentCmd);

    /**
     * 获取pod列表
     *
     * @param context 上下文信息
     * @return pod列表
     */
    public abstract List<PodInfoDTO> getPodsForDeployment(DeployContext context, ListPodCmd listPodCmd);

    /**
     * 扩缩容
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean scaleDeployment(DeployContext context, ScaleDeploymentCmd scaleDeploymentCmd);

    /**
     * 回滚发布
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean rollbackDeployment(DeployContext context);

    /**
     * deleteDeployment
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean deleteDeployment(DeployContext context, DeleteDeploymentCmd deleteDeploymentCmd);


    /**
     * createService
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean createService(DeployContext context, CreateServiceCmd createServiceCmd);

    /**
     * updateService
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean updateService(DeployContext context, ModifyServiceCmd modifyServiceCmd);

    /**
     * deleteService 删除服务
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean deleteService(DeployContext context, DeleteServiceCmd deleteServiceCmd);

    /**
     * 创建configMap
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean createConfigMap(DeployContext context, CreateConfigMapCmd createConfigMapCmd);

    /**
     * 创建或更新configMap
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean createOrUpdateConfigMap(DeployContext context, UpdateConfigMapCmd createConfigMapCmd);

    /**
     * 更新configMap
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean updateConfigMap(DeployContext context, UpdateConfigMapCmd updateConfigMapCmd);

    public abstract boolean createIngress(DeployContext context, CreateIngressCmd createIngressCmd);

    public abstract boolean createOrUpdateIngress(DeployContext context, CreateIngressCmd createIngressCmd);

    public abstract boolean deleteIngress(DeployContext context, DeleteIngressCmd deleteIngressCmd);

    public abstract boolean createNameSpace(DeployContext context, CreateNameSpaceCmd createNameSpaceCmd);

    public abstract List<V1Namespace> listNamespaces(DeployContext context);

    public abstract V1Namespace getNamespace(String name);

    public abstract boolean updateDeploymentResources(DeployContext context, UpdateResourceCmd updateResourceCmd);

    public abstract boolean updateDeploymentEnvVars(DeployContext context, UpdateEnvVarsCmd updateEnvVarsCmd);
}
