package com.rany.cake.devops.base.service.context;


import com.rany.cake.devops.base.service.plugins.JenkinsBuildPlugin;
import com.rany.cake.devops.base.service.plugins.UtCoveragePlugin;
import org.junit.Test;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/20 19:21
 * @email 18668485565163.com
 */
public class DefaultDeployPipelineTest {

    @Test
    public void start() {
        DeployPipeline pipeline = new DefaultDeployPipeline(new DeployContext());
        pipeline.addLast(new UtCoveragePlugin());
        pipeline.addLast(new JenkinsBuildPlugin());
        pipeline.start();
    }
}