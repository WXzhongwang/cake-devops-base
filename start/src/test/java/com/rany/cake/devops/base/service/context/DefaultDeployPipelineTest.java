package com.rany.cake.devops.base.service.context;


import com.rany.cake.devops.base.CakeDevopsBaseApplication;
import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.aggregate.Cluster;
import com.rany.cake.devops.base.domain.aggregate.Namespace;
import com.rany.cake.devops.base.domain.aggregate.Release;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.pk.NamespaceId;
import com.rany.cake.devops.base.domain.pk.ReleaseId;
import com.rany.cake.devops.base.domain.repository.AppRepository;
import com.rany.cake.devops.base.domain.repository.ClusterRepository;
import com.rany.cake.devops.base.domain.repository.NameSpaceRepository;
import com.rany.cake.devops.base.domain.repository.ReleaseRepository;
import com.rany.cake.devops.base.service.ReleaseCenter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/20 19:21
 * @email 18668485565163.com
 */
//@SpringBootTest(classes = CakeDevopsBaseApplication.class, properties = {
//        "websocket.enabled=false",
//        "ssh.shell.enable=false",
//        "shenyu.register.enabled=false"})
// @RunWith(SpringRunner.class)

@SpringBootTest(classes = CakeDevopsBaseApplication.class)
@TestPropertySource(properties = {"websocket.enabled=false", "ssh.shell.enable=false"})
public class DefaultDeployPipelineTest {
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


    @Test
    public void test01_testApp() {
        App app = appRepository.find(new AppId("781513981771788288"));
        Assertions.assertNotNull(app);
    }

    @Test
    public void start() {
        Release release = releaseRepository.find(new ReleaseId("906359016366682112"));
        App app = appRepository.find(release.getAppId());
        AppEnv appEnv = appRepository.getAppEnv(release.getEnvId());
        Cluster cluster = clusterRepository.find(appEnv.getClusterId());
        Namespace namespace = nameSpaceRepository.find(new NamespaceId("1"));
        releaseCenter.release(release, app, appEnv, namespace, cluster);
    }
}