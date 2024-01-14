package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.release.CreateReleaseCommand;
import com.rany.cake.devops.base.api.command.release.DeployCommand;
import com.rany.cake.devops.base.api.dto.ReleaseDTO;
import com.rany.cake.devops.base.api.query.ReleasePageQuery;

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
     * 创建发布变更
     *
     * @param createReleaseCommand 创建发布
     * @return 是否成功
     */
    PojoResult<Boolean> createRelease(CreateReleaseCommand createReleaseCommand);

    /**
     * 分页查询发布单
     *
     * @param releasePageQuery
     * @return
     */
    PageResult<ReleaseDTO> pageRelease(ReleasePageQuery releasePageQuery);

    /**
     * 基于变更单发布
     *
     * @param deployCommand 立即发布
     * @return 立即发布
     */
    PojoResult<Boolean> deploy(DeployCommand deployCommand);
}
