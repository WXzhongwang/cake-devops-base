package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.repository.AppRepository;
import com.rany.cake.devops.base.infra.convertor.AppDataConvertor;
import com.rany.cake.devops.base.infra.dao.AppDao;
import com.rany.cake.devops.base.infra.mapper.AppPOMapper;
import com.rany.cake.devops.base.infra.po.AppPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

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
    private final AppDataConvertor appDataConvertor;

    @Override
    public App find(@NotNull AppId appId) {
        AppPO appPO = appPOMapper.selectByPrimaryKey(appId.getId());
        return appDataConvertor.targetToSource(appPO);
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
}
