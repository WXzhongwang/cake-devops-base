package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.cluster.CreateClusterCommand;
import com.rany.cake.devops.base.api.command.cluster.TestClusterConnectCommand;
import com.rany.cake.devops.base.api.command.namespace.CreateNamespaceCommand;
import com.rany.cake.devops.base.api.dto.ClusterDTO;
import com.rany.cake.devops.base.api.dto.NamespaceDTO;
import com.rany.cake.devops.base.api.query.namespace.NamespaceQuery;
import com.rany.cake.devops.base.api.service.ClusterService;
import com.rany.cake.devops.base.api.service.NamespaceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 集群
 */
@RestController
@RequestMapping("/api/devops/cluster")
public class ClusterController {

    @Resource
    private ClusterService clusterService;
    @Resource
    private NamespaceService namespaceService;

    @PostMapping("/create")
    public PojoResult<String> createCluster(CreateClusterCommand createClusterCommand) {
        return PojoResult.succeed(clusterService.createCluster(createClusterCommand));
    }

    @PostMapping("/create-namespace")
    public PojoResult<String> createNameSpace(CreateNamespaceCommand createNamespaceCommand) {
        return PojoResult.succeed(namespaceService.createNamespace(createNamespaceCommand));
    }

    @PostMapping("/list-namespace")
    public ListResult<NamespaceDTO> listNameSpace(NamespaceQuery namespaceQuery) {
        return ListResult.succeed(namespaceService.listNamespace(namespaceQuery));
    }

    @PostMapping("/connect")
    public PojoResult<Boolean> testConnect(TestClusterConnectCommand testClusterConnectCommand) {
        return PojoResult.succeed(clusterService.testConnect(testClusterConnectCommand));
    }

    @GetMapping("/list-all")
    public ListResult<ClusterDTO> listAll() {
        return ListResult.succeed(clusterService.listCluster());
    }
}
