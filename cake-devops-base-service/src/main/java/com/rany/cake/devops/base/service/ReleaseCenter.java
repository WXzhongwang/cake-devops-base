package com.rany.cake.devops.base.service;

import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.aggregate.Cluster;
import com.rany.cake.devops.base.domain.aggregate.Namespace;
import com.rany.cake.devops.base.domain.aggregate.Release;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.entity.DeployHistory;
import com.rany.cake.devops.base.service.context.DefaultDeployPipeline;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.context.DeployPipeline;
import com.rany.cake.devops.base.service.context.ProgressUpdater;
import com.rany.cake.devops.base.service.integration.code.RedisSerialNumberGenerator;
import com.rany.cake.devops.base.service.plugins.approval.ApprovalPlugin;
import com.rany.cake.devops.base.service.plugins.approval.DeploymentForbiddenPlugin;
import com.rany.cake.devops.base.service.plugins.ci.BuildImagePlugin;
import com.rany.cake.devops.base.service.plugins.ci.MavenBuildPlugin;
import com.rany.cake.devops.base.service.plugins.ci.WorkSpacePlugin;
import com.rany.cake.devops.base.service.plugins.deploy.KubernetesDeployPlugin;
import com.rany.cake.devops.base.service.plugins.image.PushAcrPlugin;
import com.rany.cake.devops.base.service.plugins.image.PushHarborPlugin;
import com.rany.cake.devops.base.service.plugins.machine.MachineSelectorPlugin;
import com.rany.cake.devops.base.service.plugins.scm.CheckOutPlugin;
import com.rany.cake.devops.base.service.plugins.scm.CodePlugin;
import com.rany.cake.devops.base.service.plugins.scm.RebaseMasterPlugin;
import com.rany.cake.devops.base.service.plugins.test.SonarQubePlugin;
import com.rany.cake.devops.base.util.enums.AppEnvEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ReleaseCenter {
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private ApprovalPlugin approvalPlugin;
    @Resource
    private DeploymentForbiddenPlugin deploymentForbiddenPlugin;
    @Resource
    private MachineSelectorPlugin machineSelectorPlugin;
    @Resource
    private WorkSpacePlugin workSpacePlugin;
    @Resource
    private SonarQubePlugin sonarQubePlugin;
    @Resource
    private CheckOutPlugin checkOutPlugin;
    @Resource
    private RebaseMasterPlugin rebaseMasterPlugin;
    @Resource
    private CodePlugin codePlugin;
    @Resource
    private MavenBuildPlugin mavenBuildPlugin;
    @Resource
    private BuildImagePlugin buildImagePlugin;
    @Resource
    private PushAcrPlugin pushAcrPlugin;
    @Resource
    private PushHarborPlugin pushHarborPlugin;
    @Resource
    private KubernetesDeployPlugin kubernetesDeployPlugin;
    @Resource
    @Qualifier(ThreadPoolTaskConfiguration.TaskPools.CORE)
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Resource
    private RedisSerialNumberGenerator redisSerialNumberGenerator;
    @Resource
    private ProgressUpdater progressUpdater;


    public Boolean release(String pipeKey, Release release, App app, AppEnv appEnv, Namespace namespace, Cluster cluster, DeployHistory history) {
        DeployContext deployContext = new DeployContext(pipeKey);
        deployContext.setRelease(release);
        deployContext.setApp(app);
        deployContext.setAppEnv(appEnv);
        deployContext.setCluster(cluster);
        deployContext.setNamespace(namespace);
        deployContext.setDeployHistory(history);

        DeployPipeline pipeline = new DefaultDeployPipeline(deployContext, progressUpdater);
        pipeline.addLast(approvalPlugin);
        // pipeline.addLast(deploymentForbiddenPlugin);
        pipeline.addLast(machineSelectorPlugin);
        pipeline.addLast(workSpacePlugin);

        // pipeline.addLast(checkOutPlugin);

        pipeline.addLast(codePlugin);
        pipeline.addLast(mavenBuildPlugin);
        // pipeline.addLast(sonarQubePlugin);
        pipeline.addLast(buildImagePlugin);
        pipeline.addLast(pushAcrPlugin);
        pipeline.addLast(kubernetesDeployPlugin);
        if (StringUtils.equals(appEnv.getEnv().name(), AppEnvEnum.PROD.name())) {
            // 仅线上环境存在rebase阶段
            pipeline.addLast(rebaseMasterPlugin);
        }
        // pipeline.addLast(pushHarborPlugin);
        threadPoolTaskExecutor.execute(pipeline::start);
        // pipeline.start();
        return Boolean.TRUE;
    }
}
