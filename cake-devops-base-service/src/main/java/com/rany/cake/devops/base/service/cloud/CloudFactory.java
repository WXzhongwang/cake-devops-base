package com.rany.cake.devops.base.service.cloud;

import com.rany.cake.devops.base.domain.enums.ClusterTypeEnum;
import com.rany.cake.devops.base.service.exception.K8sErrorMessage;
import com.rany.cake.devops.base.service.exception.K8sException;
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
        throw new K8sException(K8sErrorMessage.K8S_SUPPORTED_ERROR);
    }
}
