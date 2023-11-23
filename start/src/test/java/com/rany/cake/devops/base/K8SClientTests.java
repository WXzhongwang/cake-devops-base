package com.rany.cake.devops.base;

import com.rany.cake.devops.base.domain.enums.ClusterTypeEnum;
import com.rany.cake.devops.base.service.cloud.BaseCloudService;
import com.rany.cake.devops.base.service.cloud.CloudFactory;
import com.rany.cake.devops.base.service.context.DeployContext;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;

public class K8SClientTests extends BaseTests {

    @Resource
    private CloudFactory cloudFactory;

    @Test
    public void testConnect() throws IOException {
        BaseCloudService cloudService = cloudFactory.build(ClusterTypeEnum.K8S, "127.0.0.0:12345", "tokexxxx");
        boolean b = cloudService.testConnection(new DeployContext());
    }
}

