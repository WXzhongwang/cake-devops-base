package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.repository.AppRepository;
import com.rany.cake.devops.base.infra.convertor.AppDataConvertor;
import com.rany.cake.devops.base.infra.convertor.AppEnvDataConvertor;
import com.rany.cake.devops.base.infra.dao.AppDao;
import com.rany.cake.devops.base.infra.dao.AppEnvDao;
import com.rany.cake.devops.base.infra.mapper.AppPOMapper;
import com.rany.cake.devops.base.infra.po.AppEnvPO;
import com.rany.cake.devops.base.infra.po.AppPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
@AllArgsConstructor
public class AppRepositoryImpl implements AppRepository {

    private final AppPOMapper appPOMapper;
    private final AppDao appDao;
    private final AppEnvDao appEnvDao;
    private final AppDataConvertor appDataConvertor;
    private final AppEnvDataConvertor appEnvDataConvertor;

    @Override
    public App find(@NotNull AppId appId) {
        AppPO appPO = appPOMapper.selectByPrimaryKey(appId.getId());
        App app = appDataConvertor.targetToSource(appPO);
        List<AppEnvPO> appEnvPOS = appEnvDao.selectByAppId(appId.getId());
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
    public int saveAppEnv(AppEnv appEnv) {
        return appEnvDao.save(appEnv);
    }
}
