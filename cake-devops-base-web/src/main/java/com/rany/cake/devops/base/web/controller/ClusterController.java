package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.cluster.CreateClusterCommand;
import com.rany.cake.devops.base.api.command.cluster.TestClusterConnectCommand;
import com.rany.cake.devops.base.api.dto.ClusterDTO;
import com.rany.cake.devops.base.api.service.ClusterService;
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

    @PostMapping("/create")
    public PojoResult<String> createApp(CreateClusterCommand createClusterCommand) {
        return PojoResult.succeed(clusterService.createCluster(createClusterCommand));
    }

    @PostMapping("/connect")
    public PojoResult<Boolean> createAppEnv(TestClusterConnectCommand testClusterConnectCommand) {
        return PojoResult.succeed(clusterService.testConnect(testClusterConnectCommand));
    }

    @GetMapping("/listAll")
    public ListResult<ClusterDTO> listAll() {
        return ListResult.succeed(clusterService.listCluster());
    }
}
