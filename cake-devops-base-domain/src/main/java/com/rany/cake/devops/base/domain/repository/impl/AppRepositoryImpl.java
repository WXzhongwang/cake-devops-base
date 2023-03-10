package com.rany.cake.devops.base.domain.repository.impl;

import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.convertor.AppDataConvertor;
import com.rany.cake.devops.base.domain.dao.AppDao;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.repository.AppRepository;
import com.rany.cake.devops.base.infra.mapper.AppPOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
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
        return null;
    }

    @Override
    public void remove(@NotNull App app) {

    }

    @Override
    public void save(@NotNull App app) {
        appDao.save(app);
    }
}
