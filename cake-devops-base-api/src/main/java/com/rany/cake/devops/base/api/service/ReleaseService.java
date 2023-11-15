package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.release.CreateReleaseCommand;
import com.rany.cake.devops.base.api.command.release.DeployCommand;

/**
 * 发布
 *
 * @author zhongshengwang
 * @description 发布
 * @date 2023/1/15 21:14
 * @email 18668485565163.com
 */
public interface ReleaseService {

    /**
     * 创建一次发布
     *
     * @param createReleaseCommand 创建发布
     * @return 是否成功
     */
    PojoResult<Boolean> createRelease(CreateReleaseCommand createReleaseCommand);

    /**
     * 基于发布单发布
     *
     * @param deployCommand
     * @return
     */
    PojoResult<Boolean> deploy(DeployCommand deployCommand);
}
