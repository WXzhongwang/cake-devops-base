package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.aggregate.Namespace;

/**
 * 命名空间
 *
 * @author zhongshengwang
 * @description 发布
 * @date 2023/2/2 21:24
 * @email 18668485565163.com
 */
public interface NamespaceDao {


    /**
     * 新增
     *
     * @param namespace 命名空间
     * @return
     */
    int save(Namespace namespace);


    /**
     * 更新
     *
     * @param namespace 命名空间
     * @return
     */
    int update(Namespace namespace);

}