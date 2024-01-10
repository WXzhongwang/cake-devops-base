package com.rany.cake.devops.base.service.cloud;

import com.rany.cake.devops.base.service.context.DeployContext;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

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
            this.apiClient = ClientBuilder.standard().setVerifyingSsl(false).build();
//            this.apiClient = Config.fromConfig(new InputStreamReader(Files.newInputStream(Paths.get("/Users/yuanjinxiu/.kube/config"))))
//                    .setBasePath("https://kubernetes.docker.internal:6443")
//                    .setVerifyingSsl(false);
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
     * 更新configMap
     *
     * @param context 上下文信息
     * @return 是否成功
     */
    public abstract boolean updateConfigMap(DeployContext context);

    public abstract boolean createIngress(DeployContext context);

    public abstract boolean deleteIngress(DeployContext context);

    public abstract boolean createNameSpace(DeployContext context);
}
