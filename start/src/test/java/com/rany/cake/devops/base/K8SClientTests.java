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
        System.setProperty("https.protocols", "TLSv1.2");
        System.setProperty("javax.net.debug", "ssl");
        BaseCloudService cloudService = new K8sCloudService("https://kubernetes.docker.internal:6443", "eyJhbGciOiJSUzI1NiIsImtpZCI6IjlKakdFX2ZNbDlMaG4ycnFEN2VnS3ZzSmNycDlKNk8tbVUzR2p4UmpVVm8ifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6Im15LXNlcnZpY2UtYWNjb3VudC10b2tlbi1qc2s0aCIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50Lm5hbWUiOiJteS1zZXJ2aWNlLWFjY291bnQiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC51aWQiOiIwZDEwYWZiMS1mOTZiLTRlMzktYjdmYS1lZTFhZmRkYzQ0ODUiLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6ZGVmYXVsdDpteS1zZXJ2aWNlLWFjY291bnQifQ.bEVRZyPnaNdNUJH3zsVBPyBN_2BItq5URxMu-bhVXKyjciJdmhNa3yzzuwBiJR-QnjRoPGrTvnZeQyb71R1JhWG-92AsZ1Bi0PrN2MtAANe7r9zQmIDsPe20n3zXZrBdFKdFNqvu6aDgA4xkBk1Y5bYF_3-mT3Kp3Lckm9jwhtV_kjBf8ZGaZK6dUDzhhbZKi-BQXQOY-VcBAQKo180waf5tKJV9ttl-BbQHJOxV5bYu63H15vSj2U4P3OBT2uj1VPuzrQmRbo0q_Efc3yz8PEoDTSLAlc3E0KIxa6Ec2wUuoPOXhRsIuW9Ue8dwGmupnb9WdPf9JvR2ReasWfai7A%");
        //BaseCloudService cloudService = new K8sCloudService(null, "");
        boolean connected = cloudService.testConnection(new DeployContext(null));
        Assert.assertTrue(connected);
    }
}

