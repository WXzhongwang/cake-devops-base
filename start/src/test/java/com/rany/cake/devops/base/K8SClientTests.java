package com.rany.cake.devops.base;

import com.rany.cake.devops.base.service.cloud.BaseCloudService;
import com.rany.cake.devops.base.service.cloud.K8sCloudService;
import com.rany.cake.devops.base.service.context.DeployContext;
import org.junit.Assert;
import org.junit.Test;


public class K8SClientTests {


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
}

