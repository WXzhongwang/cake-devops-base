package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.cake.framework.ddd.repository.Repository;
import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.repository.param.AppQueryParam;

import java.util.List;

/**
 * AppRepository
 *
 * @author zhongshengwang
 * @description appRepository
 * @date 2023/1/28 21:01
 * @email 18668485565163.com
 */
public interface AppRepository extends Repository<App, AppId> {
    int update(App app);

    void saveAppEnv(AppEnv appEnv);

    AppEnv getAppEnv(Long envId);

    List<AppEnv> listAppEnv(Long appId);
    Page<App> pageApp(AppQueryParam appQueryParam);
}
