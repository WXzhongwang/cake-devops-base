package com.rany.cake.devops.base;

import com.rany.cake.devops.base.domain.aggregate.Namespace;
import com.rany.cake.devops.base.domain.pk.ClusterId;
import com.rany.cake.devops.base.domain.pk.NamespaceId;
import com.rany.cake.devops.base.domain.type.NamespaceName;
import com.rany.cake.devops.base.service.cloud.BaseCloudService;
import com.rany.cake.devops.base.service.cloud.K8sCloudService;
import com.rany.cake.devops.base.service.code.RepoUrlUtils;
import com.rany.cake.devops.base.service.context.DeployContext;
import io.kubernetes.client.openapi.models.V1Namespace;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


/**
 * ClientTest
 *
 * @author zhongwang
 * @date 2024/7/24
 * @slogan Why Not
 * @email zhongshengwang.zsw@alibaba-inc.com
 */
public class ClientTest {

    @Test
    public void testCreateNamespace() {

        BaseCloudService cloudService = new K8sCloudService("https://kubernetes.docker.internal:6443", "");
        DeployContext context = new DeployContext(new String("12345"));
        context.setNamespace(new Namespace(new NamespaceId("12345"), new NamespaceName("cake-devops-base"), new ClusterId("1L")));
        boolean nameSpace = cloudService.createNameSpace(context);
        Assert.assertTrue(nameSpace);
    }

    @Test
    public void testListNamespace() {
        BaseCloudService cloudService = new K8sCloudService("https://kubernetes.docker.internal:6443", "");
        DeployContext context = new DeployContext(new String("12345"));
        // context.setNamespace(new Namespace(new NamespaceId("12345"), new NamespaceName("cake-devops"), new ClusterId("1L")));
        List<V1Namespace> v1Namespaces = cloudService.listNamespaces(context);
        Assert.assertFalse(v1Namespaces.isEmpty());
    }

    @Test
    public void testExtract() {
        String repoUrl = "https://github.com/WXzhongwang/cake-ops.git";
        String[] result = RepoUrlUtils.extractRepoInfo(repoUrl);
        if (result.length == 0) {
            System.out.println("Invalid GitHub URL");
        }
    }
}
