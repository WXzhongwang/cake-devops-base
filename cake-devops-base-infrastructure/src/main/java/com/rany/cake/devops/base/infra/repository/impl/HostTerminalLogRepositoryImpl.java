package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.entity.HostTerminalLog;
import com.rany.cake.devops.base.domain.repository.HostTerminalLogRepository;
import com.rany.cake.devops.base.infra.convertor.HostTerminalLogDataConvertor;
import com.rany.cake.devops.base.infra.dao.HostTerminalLogDao;
import com.rany.cake.devops.base.infra.mapper.HostTerminalLogPOMapper;
import com.rany.cake.devops.base.infra.po.HostTerminalLogPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HostTerminalLogRepositoryImpl implements HostTerminalLogRepository {
    private final HostTerminalLogPOMapper hostTerminalLogPOMapper;
    private final HostTerminalLogDao hostTerminalLogDao;
    private final HostTerminalLogDataConvertor hostTerminalConfigDataConvertor;

    @Override
    public HostTerminalLog getConfig(String accessToken) {
        HostTerminalLogPO accessLog = hostTerminalLogDao.getAccessLog(accessToken);
        return hostTerminalConfigDataConvertor.targetToSource(accessLog);
    }

    @Override
    public void remove(HostTerminalLog log) {
        hostTerminalLogDao.update(log);
    }

    @Override
    public Long save(HostTerminalLog log) {
        hostTerminalLogDao.save(log);
        return log.getId();
    }

    @Override
    public int update(HostTerminalLog log) {
        return hostTerminalLogDao.update(log);
    }
}
