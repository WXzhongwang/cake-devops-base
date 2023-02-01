package com.rany.cake.devops.base.service.cloud;

import com.rany.cake.devops.base.service.context.DeployContext;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/20 19:46
 * @email 18668485565163.com
 */
public abstract class BaseCloudService {

    /**
     * createDeployment
     *
     * @param context
     * @return
     */
    public abstract boolean createDeployment(DeployContext context);

    /**
     * deleteDeployment
     *
     * @param context
     * @return
     */
    public abstract boolean deleteDeployment(DeployContext context);


    /**
     * createService
     *
     * @param context
     * @return
     */
    public abstract boolean createService(DeployContext context);


    /**
     * updateService
     *
     * @param context
     * @return
     */
    public abstract boolean updateService(DeployContext context);

}
