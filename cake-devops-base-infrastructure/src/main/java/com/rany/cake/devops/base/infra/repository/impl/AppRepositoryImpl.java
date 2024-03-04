package com.rany.cake.devops.base.infra.repository.impl;


import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.repository.AppRepository;
import com.rany.cake.devops.base.domain.repository.param.AppQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.infra.aop.PagingQuery;
import com.rany.cake.devops.base.infra.convertor.AppDataConvertor;
import com.rany.cake.devops.base.infra.convertor.AppEnvDataConvertor;
import com.rany.cake.devops.base.infra.dao.AppDao;
import com.rany.cake.devops.base.infra.dao.AppEnvDao;
import com.rany.cake.devops.base.infra.mapper.AppEnvPOMapper;
import com.rany.cake.devops.base.infra.mapper.AppPOMapper;
import com.rany.cake.devops.base.infra.po.AppEnvPO;
import com.rany.cake.devops.base.infra.po.AppPO;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 应用
 *
 * @author zhongshengwang
 * @description 应用
 * @date 2023/1/28 21:02
 * @email 18668485565163.com
 */
@Service
public class AppRepositoryImpl implements AppRepository {
    private @Resource AppPOMapper appPOMapper;

    private @Resource AppDao appDao;

    private @Resource AppEnvDao appEnvDao;
    private @Resource AppEnvPOMapper appEnvPOMapper;
    private @Resource AppDataConvertor appDataConvertor;
    private @Resource AppEnvDataConvertor appEnvDataConvertor;

    @Override
    public App find(@NotNull AppId appId) {
        AppPO appPO = appDao.selectByAppId(appId.getAppId());
        App app = appDataConvertor.targetToSource(appPO);
        List<AppEnvPO> appEnvPOS = appEnvDao.selectByAppId(appId.getAppId());
        List<AppEnv> appEnvs = appEnvDataConvertor.targetToSource(appEnvPOS);
        app.setAppEnvList(appEnvs);
        return app;
    }

    @Override
    public void remove(@NotNull App app) {
        AppPO appPO = appDataConvertor.sourceToTarget(app);
        appPO.setIsDeleted(DeleteStatusEnum.YES.getValue());
        appPOMapper.updateByPrimaryKey(appPO);
    }

    @Override
    public void save(@NotNull App app) {
        appDao.save(app);
    }

    @Override
    public int update(App app) {
        return appDao.update(app);
    }

    @Override
    public void saveAppEnv(AppEnv appEnv) {
        appEnvDao.save(appEnv);
    }

    @Override
    public void updateAppEnv(AppEnv appEnv) {
        appEnvDao.update(appEnv);
    }

    @Override
    public AppEnv getAppEnv(String envId) {
        AppEnvPO appEnvPO = appEnvDao.selectByEnvId(envId);
        return appEnvDataConvertor.targetToSource(appEnvPO);
    }

    @Override
    public List<AppEnv> listAppEnv(String appId) {
        List<AppEnvPO> appEnvPOS = appEnvDao.selectByAppId(appId);
        return appEnvDataConvertor.targetToSource(appEnvPOS);
    }

    @PagingQuery
    public Page<App> pageApp(AppQueryParam appQueryParam) {
        List<AppPO> appPOS = appDao.queryApp(appQueryParam);
        PageInfo<AppPO> pageInfo = new PageInfo<>(appPOS);
        List<App> apps = appDataConvertor.targetToSource(appPOS);
        return PageUtils.build(pageInfo, apps);
    }
}
