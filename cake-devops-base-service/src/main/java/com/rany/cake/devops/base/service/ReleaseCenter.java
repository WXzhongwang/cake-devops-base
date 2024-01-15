package com.rany.cake.devops.base.service;

import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.aggregate.Cluster;
import com.rany.cake.devops.base.domain.aggregate.Namespace;
import com.rany.cake.devops.base.domain.aggregate.Release;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.service.code.RedisSerialNumberGenerator;
import com.rany.cake.devops.base.service.context.DefaultDeployPipeline;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.context.DeployPipeline;
import com.rany.cake.devops.base.service.context.ProgressUpdater;
import com.rany.cake.devops.base.service.plugins.approval.ApprovalPlugin;
import com.rany.cake.devops.base.service.plugins.approval.DeploymentForbiddenPlugin;
import com.rany.cake.devops.base.service.plugins.ci.DeliveryPlugin;
import com.rany.cake.devops.base.service.plugins.machine.MachineSelectorPlugin;
import com.rany.cake.devops.base.service.plugins.scm.CheckOutPlugin;
import com.rany.cake.devops.base.service.plugins.test.SonarQubePlugin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ReleaseCenter {

    @Resource
    private StringRedisTemplate redisTemplate;
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
    @Resource
    @Qualifier(ThreadPoolTaskConfiguration.TaskPools.CORE)
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Resource
    private RedisSerialNumberGenerator redisSerialNumberGenerator;


    public Boolean release(Release release, App app, AppEnv appEnv, Namespace namespace, Cluster cluster) {
        String pipeKey = redisSerialNumberGenerator.generatePipeNumber(release.getReleaseNo());
        DeployContext deployContext = new DeployContext(pipeKey);
        deployContext.setRelease(release);
        deployContext.setApp(app);
        deployContext.setAppEnv(appEnv);
        deployContext.setCluster(cluster);
        deployContext.setNamespace(namespace);

        DeployPipeline pipeline = new DefaultDeployPipeline(deployContext, new ProgressUpdater(redisTemplate));
        pipeline.addLast(machineSelectorPlugin);
        pipeline.addLast(approvalPlugin);
        pipeline.addLast(deploymentForbiddenPlugin);
        pipeline.addLast(checkOutPlugin);
        pipeline.addLast(sonarQubePlugin);
        pipeline.addLast(deliveryPlugin);
        threadPoolTaskExecutor.execute(pipeline::start);
        return Boolean.TRUE;
    }
}
