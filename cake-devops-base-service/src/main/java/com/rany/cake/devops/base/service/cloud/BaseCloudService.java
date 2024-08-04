package com.rany.cake.devops.base.service.cloud;

import com.rany.cake.devops.base.service.context.DeployContext;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.util.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

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
    public abstract boolean createDeployment(DeployContext context);

    /**
     * createOrUpdateDeployment
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean createOrUpdateDeployment(DeployContext context);

    /**
     * 扩缩容
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean scaleDeployment(DeployContext context);

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
     * 创建或更新configMap
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean createOrUpdateConfigMap(DeployContext context);

    /**
     * 更新configMap
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean updateConfigMap(DeployContext context);

    public abstract boolean createIngress(DeployContext context);

    public abstract boolean createOrUpdateIngress(DeployContext context);

    public abstract boolean deleteIngress(DeployContext context);

    public abstract boolean createNameSpace(DeployContext context);

    public abstract List<V1Namespace> listNamespaces(DeployContext context);

    public abstract V1Namespace getNamespace(DeployContext context);

    public abstract V1Namespace getNamespace(String name);

    /**
     * 将给定的版本号加1。
     *
     * @param version 当前版本号，格式为 "vX"，其中 X 是数字。
     * @return 加1后的版本号。
     */
    protected String incrementVersion(String version) {
        if (version == null) {
            return "";
        }
        // 检查版本号格式
        if (version.length() <= 1) {
            throw new IllegalArgumentException("Invalid version format: " + version);
        }

        // 提取数字部分并转换为 BigInteger
        BigInteger number = new BigInteger(version);

        // 将数字加1
        BigInteger incrementedNumber = number.add(BigInteger.ONE);

        // 构建新的版本号
        return incrementedNumber.toString();
    }
}
