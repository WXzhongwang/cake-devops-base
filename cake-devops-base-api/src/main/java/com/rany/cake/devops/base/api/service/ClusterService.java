package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.CreateClusterCommand;
import com.rany.cake.devops.base.api.command.TestClusterConnectCommand;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 21:18
 * @email 18668485565163.com
 */
public interface ClusterService {


    /**
     * 测试链接k8s集群
     *
     * @param testClusterConnectCommand
     * @return
     */
    PojoResult<Boolean> testConnect(TestClusterConnectCommand testClusterConnectCommand);

    /**
     * 创建集群
     *
     * @param createClusterCommand
     * @return
     */
    PojoResult<Long> createCluster(CreateClusterCommand createClusterCommand);

}
