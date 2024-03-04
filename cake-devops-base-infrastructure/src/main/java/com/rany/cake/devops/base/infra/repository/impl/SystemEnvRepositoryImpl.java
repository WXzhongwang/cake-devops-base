package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.entity.SystemEnv;
import com.rany.cake.devops.base.domain.repository.SystemEnvRepository;
import com.rany.cake.devops.base.infra.convertor.SystemEnvDataConvertor;
import com.rany.cake.devops.base.infra.mapper.SystemEnvPOMapper;
import com.rany.cake.devops.base.infra.po.SystemEnvPO;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import org.springframework.stereotype.Service;

@Service
public class SystemEnvRepositoryImpl implements SystemEnvRepository {
    private SystemEnvPOMapper systemEnvPOMapper;
    private SystemEnvDataConvertor systemEnvDataConvertor;

    @Override
    public SystemEnv find(Long id) {
        SystemEnvPO systemEnvPO = systemEnvPOMapper.selectByPrimaryKey(id);
        return systemEnvDataConvertor.targetToSource(systemEnvPO);
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
}
