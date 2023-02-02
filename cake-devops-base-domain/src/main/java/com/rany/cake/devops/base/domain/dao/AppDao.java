package com.rany.cake.devops.base.domain.dao;

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
     * 根据账号获取member
     *
     * @param app
     * @return
     */
    int save(App app);

}
