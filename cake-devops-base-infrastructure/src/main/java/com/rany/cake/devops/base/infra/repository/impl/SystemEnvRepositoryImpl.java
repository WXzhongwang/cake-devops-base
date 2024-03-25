package com.rany.cake.devops.base.infra.repository.impl;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.cake.devops.base.domain.entity.SystemEnv;
import com.rany.cake.devops.base.domain.repository.SystemEnvRepository;
import com.rany.cake.devops.base.domain.repository.param.SystemEnvPageQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.infra.aop.PagingQuery;
import com.rany.cake.devops.base.infra.convertor.SystemEnvDataConvertor;
import com.rany.cake.devops.base.infra.dao.SystemEnvDao;
import com.rany.cake.devops.base.infra.mapper.SystemEnvPOMapper;
import com.rany.cake.devops.base.infra.po.SystemEnvPO;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SystemEnvRepositoryImpl implements SystemEnvRepository {
    private final SystemEnvPOMapper systemEnvPOMapper;
    private final SystemEnvDao systemEnvDao;
    private final SystemEnvDataConvertor systemEnvDataConvertor;

    @Override
    public SystemEnv find(Long id) {
        SystemEnvPO systemEnvPO = systemEnvPOMapper.selectByPrimaryKey(id);
        return systemEnvDataConvertor.targetToSource(systemEnvPO);
    }

    @Override
    public SystemEnv findByName(String name) {
        return null;
    }

    @Override
    public void remove(SystemEnv env) {
        SystemEnvPO systemEnvPO = systemEnvPOMapper.selectByPrimaryKey(env.getId());
        systemEnvPO.setIsDeleted(DeleteStatusEnum.YES.getValue());
        systemEnvPOMapper.updateByPrimaryKey(systemEnvPO);
    }

    @Override
    public void save(SystemEnv env) {
        SystemEnvPO systemEnvPO = systemEnvDataConvertor.sourceToTarget(env);
        systemEnvPOMapper.insertSelective(systemEnvPO);

    }

    @Override
    public void batchSave(List<SystemEnv> envs) {
        for (SystemEnv systemEnv : envs) {
            systemEnvDao.save(systemEnv);
        }
    }

    @Override
    public void update(SystemEnv env) {
        systemEnvDao.update(env);
    }

    @Override
    @PagingQuery
    public Page<SystemEnv> page(SystemEnvPageQueryParam queryParam) {
        List<SystemEnvPO> hostEnvPOS = systemEnvDao.querySystemEnv(queryParam);
        PageInfo<SystemEnvPO> pageInfo = new PageInfo<>(hostEnvPOS);
        List<SystemEnv> configs = systemEnvDataConvertor.targetToSource(hostEnvPOS);
        return PageUtils.build(pageInfo, configs);
    }
}
