package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.entity.HostTerminalConfig;
import com.rany.cake.devops.base.domain.repository.HostTerminalConfigRepository;
import com.rany.cake.devops.base.infra.convertor.HostTerminalConfigDataConvertor;
import com.rany.cake.devops.base.infra.dao.HostTerminalConfigDao;
import com.rany.cake.devops.base.infra.mapper.HostTerminalConfigPOMapper;
import com.rany.cake.devops.base.infra.po.HostTerminalConfigPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HostTerminalConfigRepositoryImpl implements HostTerminalConfigRepository {

    private final HostTerminalConfigPOMapper hostTerminalConfigPOMapper;
    private final HostTerminalConfigDao hostTerminalConfigDao;
    private final HostTerminalConfigDataConvertor hostTerminalConfigDataConvertor;

    @Override
    public HostTerminalConfig getConfig(String hostId) {
        HostTerminalConfigPO configPO = hostTerminalConfigDao.findByHostId(hostId);
        return hostTerminalConfigDataConvertor.targetToSource(configPO);
    }

    @Override
    public void remove(HostTerminalConfig config) {
        hostTerminalConfigDao.update(config);
    }

    @Override
    public void save(HostTerminalConfig config) {
        hostTerminalConfigDao.save(config);
    }

    @Override
    public int update(HostTerminalConfig config) {
        return hostTerminalConfigDao.update(config);
    }
}
