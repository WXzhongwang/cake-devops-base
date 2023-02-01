package com.rany.cake.devops.base.service.cloud;

import com.rany.cake.devops.base.service.context.DeployContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * K8sCloudService
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/20 20:51
 * @email 18668485565163.com
 */
@Slf4j
@Service
public class K8sCloudService extends BaseCloudService {
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
