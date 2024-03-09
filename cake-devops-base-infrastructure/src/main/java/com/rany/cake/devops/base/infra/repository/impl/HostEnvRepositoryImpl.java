package com.rany.cake.devops.base.infra.repository.impl;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.cake.devops.base.domain.entity.HostEnv;
import com.rany.cake.devops.base.domain.repository.HostEnvRepository;
import com.rany.cake.devops.base.domain.repository.param.HostEnvQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.infra.aop.PagingQuery;
import com.rany.cake.devops.base.infra.convertor.HostEnvDataConvertor;
import com.rany.cake.devops.base.infra.dao.HostEnvDao;
import com.rany.cake.devops.base.infra.mapper.HostEnvPOMapper;
import com.rany.cake.devops.base.infra.po.HostEnvPO;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class HostEnvRepositoryImpl implements HostEnvRepository {
    private HostEnvPOMapper hostEnvPOMapper;
    private HostEnvDao hostEnvDao;
    private HostEnvDataConvertor hostEnvDataConvertor;

    @Override
    public HostEnv find(Long id) {
        HostEnvPO hostEnvPO = hostEnvPOMapper.selectByPrimaryKey(id);
        return hostEnvDataConvertor.targetToSource(hostEnvPO);
    }

    @Override
    public void remove(HostEnv env) {
        HostEnvPO hostEnvPO = hostEnvPOMapper.selectByPrimaryKey(env.getId());
        hostEnvPO.setIsDeleted(DeleteStatusEnum.YES.getValue());
        hostEnvPOMapper.updateByPrimaryKey(hostEnvPO);
    }

    @Override
    public void save(HostEnv env) {
        HostEnvPO hostEnvPO = hostEnvDataConvertor.sourceToTarget(env);
        hostEnvPOMapper.insertSelective(hostEnvPO);
    }

    @Override
    public void update(HostEnv env) {
        HostEnvPO hostEnvPO = hostEnvDataConvertor.sourceToTarget(env);
        hostEnvPOMapper.updateByPrimaryKey(hostEnvPO);
    }

    @Override
    @PagingQuery
    public Page<HostEnv> page(HostEnvQueryParam queryParam) {
        List<HostEnvPO> hostEnvPOS = hostEnvDao.queryHostEnv(queryParam);
        PageInfo<HostEnvPO> pageInfo = new PageInfo<>(hostEnvPOS);
        List<HostEnv> configs = hostEnvDataConvertor.targetToSource(hostEnvPOS);
        return PageUtils.build(pageInfo, configs);
    }
}
