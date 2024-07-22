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
import com.rany.cake.devops.base.service.ReleaseCenter;
import com.rany.cake.devops.base.service.cloud.BaseCloudService;
import com.rany.cake.devops.base.service.cloud.CloudFactory;
import com.rany.cake.devops.base.service.cloud.K8sCloudService;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.handler.host.HostConnectionService;
import com.rany.cake.devops.base.util.enums.ClusterTypeEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;


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
    public void testCreateNamespace() {
        BaseCloudService cloudService = cloudFactory.build(ClusterTypeEnum.K8S, "https://kubernetes.docker.internal:6443", "");
        Namespace namespace = nameSpaceRepository.find(new NamespaceId("1"));

        DeployContext context = new DeployContext(new String("12345"));
        context.setNamespace(namespace);
        boolean nameSpace = cloudService.createNameSpace(context);
        Assert.assertTrue(nameSpace);
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
        
        boolean deployment = cloudService.createDeployment(context);
        Assert.assertTrue(deployment);
    }
}

