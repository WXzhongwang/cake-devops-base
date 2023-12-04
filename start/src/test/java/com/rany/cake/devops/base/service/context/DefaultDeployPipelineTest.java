package com.rany.cake.devops.base.service.context;


import com.rany.cake.devops.base.BaseTests;
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
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/20 19:21
 * @email 18668485565163.com
 */
public class DefaultDeployPipelineTest extends BaseTests {
    @Resource
    private AppRepository appRepository;
    @Resource
    private ClusterRepository clusterRepository;
    @Resource
    private NameSpaceRepository nameSpaceRepository;
    @Resource
    private ReleaseRepository releaseRepository;

    @Resource
    private ReleaseCenter releaseCenter;


    @Test
    public void test01_testApp() {
        App app = appRepository.find(new AppId(781513981771788288L));
        Assert.assertNotNull(app);
    }

    @Test
    public void start() {
        Release release = releaseRepository.find(new ReleaseId(1L));
        App app = appRepository.find(release.getAppId());
        AppEnv appEnv = appRepository.getAppEnv(release.getEnvId());
        Cluster cluster = clusterRepository.find(appEnv.getClusterId());
        Namespace namespace = nameSpaceRepository.find(new NamespaceId(1L));
        releaseCenter.release(release, app, appEnv, namespace, cluster);
    }
}