package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.cake.framework.ddd.repository.Repository;
import com.rany.cake.devops.base.domain.aggregate.Release;
import com.rany.cake.devops.base.domain.pk.ReleaseId;
import com.rany.cake.devops.base.domain.repository.param.ReleasePageQueryParam;

/**
 * 发布表仓储
 *
 * @author zhongshengwang
 * @description 发布表仓储
 * @date 2023/1/28 21:01
 * @email 18668485565163.com
 */
public interface ReleaseRepository extends Repository<Release, ReleaseId> {

    int update(Release release);

    Page<Release> pageRelease(ReleasePageQueryParam releasePageQueryParam);
}
