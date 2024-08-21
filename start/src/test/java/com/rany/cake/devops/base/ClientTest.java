package com.rany.cake.devops.base;

import com.rany.cake.devops.base.domain.aggregate.Namespace;
import com.rany.cake.devops.base.domain.pk.ClusterId;
import com.rany.cake.devops.base.domain.pk.NamespaceId;
import com.rany.cake.devops.base.domain.type.NamespaceName;
import com.rany.cake.devops.base.service.cloud.BaseCloudService;
import com.rany.cake.devops.base.service.cloud.K8sCloudService;
import com.rany.cake.devops.base.service.cloud.dto.CreateNameSpaceCmd;
import com.rany.cake.devops.base.service.cloud.dto.ListPodCmd;
import com.rany.cake.devops.base.service.cloud.dto.PodInfoDTO;
import com.rany.cake.devops.base.service.code.RepoUrlUtils;
import com.rany.cake.devops.base.service.context.DeployContext;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.util.Config;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;


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
        context.setNamespace(new Namespace(new NamespaceId("12345"), new NamespaceName("cake-honda"), new ClusterId("1L")));
        boolean nameSpace = cloudService.createNameSpace(context, new CreateNameSpaceCmd("cake-honda"));
        Assert.assertTrue(nameSpace);
    }

    @Test
    public void testListDeploymentPod() {
        K8sCloudService cloudService = new K8sCloudService("https://kubernetes.docker.internal:6443", "");
        DeployContext context = new DeployContext();
        ListPodCmd listPodCmd = new ListPodCmd();
        listPodCmd.setDeploymentName("cake-devops-base");
        listPodCmd.setDeploymentName("cake-honda");
        List<PodInfoDTO> podsForDeployment = cloudService.getPodsForDeployment(context,
                listPodCmd);
        Assert.assertFalse(podsForDeployment.isEmpty());
    }


    @Test
    public void testListNamespace() {
        BaseCloudService cloudService = new K8sCloudService("https://kubernetes.docker.internal:6443", "");
        DeployContext context = new DeployContext();
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


    private static final Logger log = LoggerFactory.getLogger(ClientTest.class);

    @Test
    public void testGetDeployment() throws IOException, ApiException {
        List<V1Deployment> deploymentsByLabel = this.findDeploymentsByLabel("cake-devops-base", "cake-honda");
        assertFalse(deploymentsByLabel.isEmpty(), "Expected at least one deployment to be found.");
    }

    public List<V1Deployment> findDeploymentsByLabel(String deploymentName, String namespace) throws IOException, ApiException {
        ApiClient apiClient = Config.defaultClient();
        AppsV1Api apiInstance = new AppsV1Api(apiClient);
        String labelSelector = "app=" + deploymentName; // 使用标准标签
        try {
            V1Deployment result = apiInstance.readNamespacedDeployment(deploymentName, namespace, null);
            if (result.getMetadata() != null) {
                log.warn("No Deployments found with labelSelector '{}'.", labelSelector);
            } else {
                log.info("Found {} Deployments with labelSelector '{}'.", result.getStatus(), labelSelector);
            }
            return Collections.singletonList(result);
        } catch (ApiException e) {
            log.error("Failed to list Deployments with labelSelector '{}'. Response: {}. Error: {}.", labelSelector, e.getResponseBody(), e.getMessage());
            return Collections.emptyList();
        }
    }
}
