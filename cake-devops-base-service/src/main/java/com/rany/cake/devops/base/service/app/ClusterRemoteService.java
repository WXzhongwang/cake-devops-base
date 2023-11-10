package com.rany.cake.devops.base.service.app;

import com.alibaba.dubbo.config.annotation.Service;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.cluster.CreateClusterCommand;
import com.rany.cake.devops.base.api.command.cluster.TestClusterConnectCommand;
import com.rany.cake.devops.base.api.service.ClusterService;
import com.rany.cake.devops.base.domain.enums.AppEnvEnum;
import com.rany.cake.devops.base.domain.enums.ClusterTypeEnum;
import com.rany.cake.devops.base.domain.service.ClusterDomainService;
import com.rany.cake.devops.base.service.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.service.exception.DevOpsException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;

/**
 * 集群相关接口
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/2/18 22:19
 * @email 18668485565163.com
 */
@Service
@Slf4j
@AllArgsConstructor
public class ClusterRemoteService implements ClusterService {

    private final ClusterDomainService clusterDomainService;

    @Override
    public PojoResult<Boolean> testConnect(TestClusterConnectCommand testClusterConnectCommand) {
        ClusterTypeEnum clusterTypeEnum = EnumUtils.getEnum(ClusterTypeEnum.class, testClusterConnectCommand.getClusterType());
        if (clusterTypeEnum == null) {
            throw new DevOpsException(DevOpsErrorMessage.OPS_SUPPORTED_ERROR);
        }
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Long> createCluster(CreateClusterCommand createClusterCommand) {
        AppEnvEnum envEnum = EnumUtils.getEnum(AppEnvEnum.class, createClusterCommand.getEnv());
        if (envEnum == null) {
            throw new DevOpsException(DevOpsErrorMessage.OPS_ENV_SUPPORTED_ERROR);
        }
        PojoResult<Boolean> connect = this.testConnect(new TestClusterConnectCommand(createClusterCommand.getConnectString(),
                createClusterCommand.getClusterType(), createClusterCommand.getToken()));
        if (!connect.getSuccess() || !connect.getContent()) {
            throw new DevOpsException(DevOpsErrorMessage.OPS_CONNECTED_ERROR);
        }
        return null;
    }
}
