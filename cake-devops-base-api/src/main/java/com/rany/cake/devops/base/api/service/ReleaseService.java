package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.release.CreateReleaseCommand;

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
     * @param createReleaseCommand
     * @return
     */
    PojoResult<Boolean> createRelease(CreateReleaseCommand createReleaseCommand);
}
