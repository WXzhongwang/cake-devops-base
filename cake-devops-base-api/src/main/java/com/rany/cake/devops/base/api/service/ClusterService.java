package com.rany.cake.devops.base.api.service;

import com.rany.cake.devops.base.api.command.cluster.CreateClusterCommand;
import com.rany.cake.devops.base.api.command.cluster.TestClusterConnectCommand;
import com.rany.cake.devops.base.api.dto.ClusterDTO;

import java.util.List;

/**
 * 集群服务
 *
 * @author zhongshengwang
 * @description 集群服务
 * @date 2023/1/15 21:18
 * @email 18668485565163.com
 */
public interface ClusterService {


    /**
     * 测试链接k8s集群
     *
     * @param testClusterConnectCommand 测试连接集群
     * @return 是否连接成功
     */
    Boolean testConnect(TestClusterConnectCommand testClusterConnectCommand);

    /**
     * 创建集群
     *
     * @param createClusterCommand 创建集群
     * @return 创建是否成功
     */
    String createCluster(CreateClusterCommand createClusterCommand);

    /**
     * 查询所有集群
     *
     * @return 全部集群列表
     */
    List<ClusterDTO> listCluster();

}
