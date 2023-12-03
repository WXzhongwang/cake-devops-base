package com.rany.cake.devops.base.web.controller;

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
import com.rany.cake.devops.base.service.context.DefaultDeployPipeline;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.context.DeployPipeline;
import com.rany.cake.devops.base.service.plugins.approval.ApprovalPlugin;
import com.rany.cake.devops.base.service.plugins.approval.DeploymentForbiddenPlugin;
import com.rany.cake.devops.base.service.plugins.ci.DeliveryPlugin;
import com.rany.cake.devops.base.service.plugins.machine.MachineSelectorPlugin;
import com.rany.cake.devops.base.service.plugins.scm.CheckOutPlugin;
import com.rany.cake.devops.base.service.plugins.test.SonarQubePlugin;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试
 */
@RestController("/test")
public class TestController {
    @Resource
    private AppRepository appRepository;
    @Resource
    private ClusterRepository clusterRepository;
    @Resource
    private NameSpaceRepository nameSpaceRepository;
    @Resource
    private ReleaseRepository releaseRepository;
    @Resource
    private ApprovalPlugin approvalPlugin;
    @Resource
    private DeploymentForbiddenPlugin deploymentForbiddenPlugin;
    @Resource
    private MachineSelectorPlugin machineSelectorPlugin;
    @Resource
    private SonarQubePlugin sonarQubePlugin;
    @Resource
    private CheckOutPlugin checkOutPlugin;
    @Resource
    private DeliveryPlugin deliveryPlugin;

    @GetMapping(path = "/deploy")
    public String deploy(@RequestParam(value = "id") String id,
                         HttpServletRequest request, HttpServletResponse response
            , ModelMap model) {
        DeployContext deployContext = new DeployContext();
        Release release = releaseRepository.find(new ReleaseId(1L));
        deployContext.setRelease(release);

        App app = appRepository.find(release.getAppId());
        deployContext.setApp(app);

        AppEnv appEnv = appRepository.getAppEnv(release.getEnvId());
        deployContext.setAppEnv(appEnv);

        Cluster cluster = clusterRepository.find(appEnv.getClusterId());
        deployContext.setCluster(cluster);

        Namespace namespace = nameSpaceRepository.find(new NamespaceId(1L));
        deployContext.setNamespace(namespace);

        DeployPipeline pipeline = new DefaultDeployPipeline(deployContext);
        pipeline.addLast(approvalPlugin);
        pipeline.addLast(deploymentForbiddenPlugin);
        //pipeline.addLast(checkOutPlugin);
        pipeline.addLast(machineSelectorPlugin);
        pipeline.addLast(sonarQubePlugin);
        pipeline.addLast(deliveryPlugin);
        pipeline.start();
        return "ok";
    }
}
