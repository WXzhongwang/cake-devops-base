package com.rany.cake.devops.base.service.context;


import com.rany.cake.devops.base.BaseTests;
import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.aggregate.Cluster;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.pk.ClusterId;
import com.rany.cake.devops.base.domain.repository.AppRepository;
import com.rany.cake.devops.base.domain.repository.ClusterRepository;
import com.rany.cake.devops.base.domain.repository.NameSpaceRepository;
import com.rany.cake.devops.base.service.plugins.approval.ApprovalPlugin;
import com.rany.cake.devops.base.service.plugins.approval.DeploymentForbiddenPlugin;
import com.rany.cake.devops.base.service.plugins.machine.MachineSelectorPlugin;
import com.rany.cake.devops.base.service.plugins.test.SonarQubePlugin;
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
    private ApprovalPlugin approvalPlugin;
    @Resource
    private DeploymentForbiddenPlugin deploymentForbiddenPlugin;
    @Resource
    private MachineSelectorPlugin machineSelectorPlugin;
    @Resource
    private SonarQubePlugin sonarQubePlugin;

    @Test
    public void start() {
        DeployContext deployContext = new DeployContext();
        App app = appRepository.find(new AppId(781513981771788288L));
        deployContext.setApp(app);
        Cluster cluster = clusterRepository.find(new ClusterId(1L));
        deployContext.setCluster(cluster);

        DeployPipeline pipeline = new DefaultDeployPipeline(deployContext);
        pipeline.addLast(approvalPlugin);
        pipeline.addLast(deploymentForbiddenPlugin);
        pipeline.addLast(machineSelectorPlugin);
        pipeline.addLast(sonarQubePlugin);
        pipeline.start();
    }
}