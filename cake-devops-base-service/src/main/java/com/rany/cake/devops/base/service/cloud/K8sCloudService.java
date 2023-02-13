package com.rany.cake.devops.base.service.cloud;

import com.rany.cake.devops.base.service.context.DeployContext;
import lombok.extern.slf4j.Slf4j;

/**
 * K8sCloudService
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/20 20:51
 * @email 18668485565163.com
 */
@Slf4j
public class K8sCloudService extends BaseCloudService {

    public K8sCloudService(String connectString, String token) {
        build(connectString, token);
    }

    @Override
    public boolean createDeployment(DeployContext context) {
        return false;
    }

    @Override
    public boolean deleteDeployment(DeployContext context) {
        return false;
    }

    @Override
    public boolean createService(DeployContext context) {
        return false;
    }

    @Override
    public boolean updateService(DeployContext context) {
        return false;
    }
}
