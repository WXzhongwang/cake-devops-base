package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.aggregate.App;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/2/2 21:24
 * @email 18668485565163.com
 */
public interface AppDao {


    /**
     * 保存
     *
     * @param app
     * @return
     */
    int save(App app);

    /**
     * 更新
     *
     * @param app
     * @return
     */
    int update(App app);

}
