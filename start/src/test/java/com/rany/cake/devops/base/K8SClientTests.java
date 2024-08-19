package com.rany.cake.devops.base;

import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.aggregate.Cluster;
import com.rany.cake.devops.base.domain.aggregate.Namespace;
import com.rany.cake.devops.base.domain.aggregate.Release;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.pk.NamespaceId;
import com.rany.cake.devops.base.domain.pk.ReleaseId;
import com.rany.cake.devops.base.domain.repository.AppRepository;
import com.rany.cake.devops.base.domain.repository.ClusterRepository;
import com.rany.cake.devops.base.domain.repository.NameSpaceRepository;
import com.rany.cake.devops.base.domain.repository.ReleaseRepository;
import com.rany.cake.devops.base.domain.service.HostDomainService;
import com.rany.cake.devops.base.domain.valueobject.ResourceStrategy;
import com.rany.cake.devops.base.service.ReleaseCenter;
import com.rany.cake.devops.base.service.cloud.BaseCloudService;
import com.rany.cake.devops.base.service.cloud.CloudFactory;
import com.rany.cake.devops.base.service.cloud.K8sCloudService;
import com.rany.cake.devops.base.service.cloud.KubernetesConstants;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.handler.host.HostConnectionService;
import com.rany.cake.devops.base.util.enums.ClusterTypeEnum;
import io.kubernetes.client.openapi.models.V1Namespace;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


public class K8SClientTests extends BaseTests {

    @Resource
    private CloudFactory cloudFactory;

    @Autowired
    private AppRepository appRepository;
    @Autowired
    private ClusterRepository clusterRepository;
    @Autowired
    private NameSpaceRepository nameSpaceRepository;
    @Autowired
    private ReleaseRepository releaseRepository;
    @Autowired
    private ReleaseCenter releaseCenter;
    @Autowired
    private HostDomainService hostDomainService;
    @Autowired
    private HostConnectionService hostConnectionService;


    @Test
    public void testConnect() {
        // System.setProperty("https.protocols", "TLSv1.2,TLSv1.3");
//        System.setProperty("https.protocols", "TLSv1.2");
//        System.setProperty("javax.net.debug", "ssl");
        BaseCloudService cloudService = new K8sCloudService("https://kubernetes.docker.internal:6443", "");
        //BaseCloudService cloudService = new K8sCloudService(null, "");
        boolean connected = cloudService.testConnection(new DeployContext(null));
        Assert.assertTrue(connected);
    }

    @Test
    public void testListNameSpace() {
        BaseCloudService cloudService = new K8sCloudService("https://kubernetes.docker.internal:6443", "");
        //BaseCloudService cloudService = new K8sCloudService(null, "");
        List<V1Namespace> v1Namespaces = cloudService.listNamespaces(new DeployContext(null));
        Assert.assertFalse(v1Namespaces.isEmpty());
    }


    @Test
    public void testCreateDeployment() {
        BaseCloudService cloudService = cloudFactory.build(ClusterTypeEnum.K8S, "https://kubernetes.docker.internal:6443", "");
        boolean connected = cloudService.testConnection(new DeployContext(null));
        Assert.assertTrue(connected);


        Release release = releaseRepository.find(new ReleaseId("906359016366682112"));
        App app = appRepository.find(release.getAppId());
        AppEnv appEnv = appRepository.getAppEnv(release.getEnvId());
        Cluster cluster = clusterRepository.find(appEnv.getClusterId());
        Namespace namespace = nameSpaceRepository.find(new NamespaceId("1"));


        DeployContext context = new DeployContext(new String("12345"));
        context.setNamespace(namespace);
        context.setApp(app);
        context.setRelease(release);
        context.setCluster(cluster);
        context.setAppEnv(appEnv);
        context.setDeploymentName(app.getAppName().getName());
        context.setServiceName(app.getAppName().getName() + "-svc");
        context.setServicePort(8080);
        context.setContainerPort(8300);
        context.setIngressName(app.getAppName().getName());
        context.setDeploymentImage("registry.cn-hangzhou.aliyuncs.com/cake-devops-base/cake-devops-base:R202407292146090014");
        boolean deployment = cloudService.createDeployment(context);
        Assert.assertTrue(deployment);
    }


    @Test
    public void testService() {
        BaseCloudService cloudService = cloudFactory.build(ClusterTypeEnum.K8S, "https://kubernetes.docker.internal:6443", "");
        boolean connected = cloudService.testConnection(new DeployContext(null));
        Assert.assertTrue(connected);


        Release release = releaseRepository.find(new ReleaseId("906359016366682112"));
        App app = appRepository.find(release.getAppId());
        AppEnv appEnv = appRepository.getAppEnv(release.getEnvId());
        Cluster cluster = clusterRepository.find(appEnv.getClusterId());
        Namespace namespace = nameSpaceRepository.find(new NamespaceId("1"));


        DeployContext context = new DeployContext(new String("12345"));
        context.setNamespace(namespace);
        context.setApp(app);
        context.setRelease(release);
        context.setCluster(cluster);
        context.setAppEnv(appEnv);

        context.setDeploymentName(app.getAppName().getName());
        context.setServiceName(app.getAppName().getName() + "-svc");
        context.setServicePort(KubernetesConstants.DEFAULT_SERVICE_PORT);
        context.setContainerPort(KubernetesConstants.DEFAULT_WEB_SERVICE_PORT);
        context.setIngressName(app.getAppName().getName());

        context.setDeploymentImage("registry.cn-hangzhou.aliyuncs.com/cake-devops-base/cake-devops-base:R202407292146090014");
        boolean deployment = cloudService.createService(context);
        Assert.assertTrue(deployment);
    }


    @Test
    public void testCreateConfigMap() {
        BaseCloudService cloudService = cloudFactory.build(ClusterTypeEnum.K8S, "https://kubernetes.docker.internal:6443", "");
        boolean connected = cloudService.testConnection(new DeployContext(null));
        Assert.assertTrue(connected);


        Release release = releaseRepository.find(new ReleaseId("906359016366682112"));
        App app = appRepository.find(release.getAppId());
        AppEnv appEnv = appRepository.getAppEnv(release.getEnvId());
        Cluster cluster = clusterRepository.find(appEnv.getClusterId());
        Namespace namespace = nameSpaceRepository.find(new NamespaceId("1"));


        DeployContext context = new DeployContext(new String("12345"));
        context.setNamespace(namespace);
        context.setApp(app);
        context.setRelease(release);
        context.setCluster(cluster);
        context.setAppEnv(appEnv);

        context.setDeploymentName(app.getAppName().getName());
        context.setServiceName(app.getAppName().getName() + "-svc");
        context.setServicePort(KubernetesConstants.DEFAULT_SERVICE_PORT);
        context.setContainerPort(KubernetesConstants.DEFAULT_WEB_SERVICE_PORT);
        context.setIngressName(app.getAppName().getName());


        HashMap<String, String> configMap = new HashMap<>();
        configMap.put("a", "123");
        configMap.put("b", "234");
        configMap.put("c", "345");
        configMap.put("d", "456");
        context.setConfigMap(configMap);
        boolean configMapCreated = cloudService.createConfigMap(context);
        Assert.assertTrue(configMapCreated);

        configMap.put("a", "234");
        configMap.put("b", "345");
        configMap.put("c", "567");
        configMap.put("d", "789");
        boolean configMapUpdated = cloudService.updateConfigMap(context);
        Assert.assertTrue(configMapUpdated);
    }

    @Test
    public void testScaleDeployment() {
        Release release = releaseRepository.find(new ReleaseId("984061302513217536"));
        App app = appRepository.find(release.getAppId());
        AppEnv appEnv = appRepository.getAppEnv(release.getEnvId());
        Cluster cluster = clusterRepository.find(appEnv.getClusterId());
        Namespace namespace = nameSpaceRepository.find(new NamespaceId("1"));


        K8sCloudService cloudService = new K8sCloudService("https://kubernetes.docker.internal:6443", "");
        DeployContext context = new DeployContext();
        context.setAppEnv(appEnv);
        appEnv.setResourceStrategy(new ResourceStrategy(3, "", "", "", ""));
        context.setApp(app);
        context.setCluster(cluster);
        context.setNamespace(namespace);
        context.setDeploymentName("cake-devops-base");
        boolean success = cloudService.scaleDeployment(context);
        Assert.assertTrue(success);
    }
}

