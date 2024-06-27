package com.rany.cake.devops.base.infra.repository.impl;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.cake.devops.base.domain.entity.HostTerminalLog;
import com.rany.cake.devops.base.domain.repository.HostTerminalLogRepository;
import com.rany.cake.devops.base.domain.repository.param.TerminalLogPageQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.infra.aop.PagingQuery;
import com.rany.cake.devops.base.infra.convertor.HostTerminalLogDataConvertor;
import com.rany.cake.devops.base.infra.dao.HostTerminalLogDao;
import com.rany.cake.devops.base.infra.mapper.HostTerminalLogPOMapper;
import com.rany.cake.devops.base.infra.po.HostTerminalLogPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public int deleteByIds(List<Long> ids) {
        return hostTerminalLogDao.deleteByIds(ids);
    }

    @Override
    @PagingQuery
    public Page<HostTerminalLog> pageQueryTerminalLog(TerminalLogPageQueryParam param) {
        List<HostTerminalLogPO> logs = hostTerminalLogDao.pageQueryTerminalLog(param);
        PageInfo<HostTerminalLogPO> pageInfo = new PageInfo<>(logs);
        List<HostTerminalLog> terminalLogs = hostTerminalConfigDataConvertor.targetToSource(logs);
        return PageUtils.build(pageInfo, terminalLogs);
    }
}
