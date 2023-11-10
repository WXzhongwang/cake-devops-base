package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.aggregate.Release;

/**
 * 发布
 *
 * @author zhongshengwang
 * @description 发布
 * @date 2023/2/2 21:24
 * @email 18668485565163.com
 */
public interface ReleaseDao {


    /**
     * 新增
     *
     * @param release 发布
     * @return
     */
    int save(Release release);


    /**
     * 更新
     *
     * @param release 发布
     * @return
     */
    int update(Release release);

}
