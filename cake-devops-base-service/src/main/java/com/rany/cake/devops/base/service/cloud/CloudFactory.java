package com.rany.cake.devops.base.service.cloud;

import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.domain.enums.ClusterTypeEnum;
import org.springframework.stereotype.Component;

/**
 * CloudFactory
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/2/13 22:28
 * @email 18668485565163.com
 */
@Component
public class CloudFactory {
    public BaseCloudService build(ClusterTypeEnum clusterType, String connectString, String token) {
        if (clusterType == ClusterTypeEnum.K8S) {
            return new K8sCloudService(connectString, token);
        }
        throw new DevOpsException(DevOpsErrorMessage.OPS_CONNECTED_ERROR);
    }
}
