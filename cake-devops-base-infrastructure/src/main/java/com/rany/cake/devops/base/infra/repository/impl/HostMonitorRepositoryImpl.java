package com.rany.cake.devops.base.infra.repository.impl;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.cake.devops.base.domain.entity.HostMonitor;
import com.rany.cake.devops.base.domain.repository.HostMonitorRepository;
import com.rany.cake.devops.base.domain.repository.param.HostMonitorPageQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.infra.aop.PagingQuery;
import com.rany.cake.devops.base.infra.convertor.HostMonitorDataConvertor;
import com.rany.cake.devops.base.infra.dao.HostMonitorDao;
import com.rany.cake.devops.base.infra.mapper.HostMonitorPOMapper;
import com.rany.cake.devops.base.infra.po.HostMonitorPO;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HostMonitorRepositoryImpl implements HostMonitorRepository {

    private final HostMonitorPOMapper hostMonitorPOMapper;
    private final HostMonitorDao hostMonitorDao;
    private final HostMonitorDataConvertor hostMonitorDataConvertor;

    @Override
    public HostMonitor find(Long id) {
        HostMonitorPO hostMonitorPO = hostMonitorPOMapper.selectByPrimaryKey(id);
        return hostMonitorDataConvertor.targetToSource(hostMonitorPO);
    }

    @Override
    public HostMonitor findByHostId(String hostId) {
        HostMonitorPO hostMonitorPO = hostMonitorDao.selectByHostId(hostId);
        return hostMonitorDataConvertor.targetToSource(hostMonitorPO);
    }

    @Override
    public void remove(HostMonitor hostMonitor) {
        hostMonitor.setIsDeleted(DeleteStatusEnum.YES.getValue());
        hostMonitorDao.update(hostMonitor);
    }

    @Override
    public void save(HostMonitor hostMonitor) {
        hostMonitorDao.save(hostMonitor);
    }

    @Override
    public void update(HostMonitor hostMonitor) {
        hostMonitorDao.update(hostMonitor);
    }

    @Override
    @PagingQuery
    public Page<HostMonitor> page(HostMonitorPageQueryParam queryParam) {
        List<HostMonitorPO> hostMonitorPOS = hostMonitorDao.queryByParam(queryParam);
        PageInfo<HostMonitorPO> pageInfo = new PageInfo<>(hostMonitorPOS);
        List<HostMonitor> configs = hostMonitorDataConvertor.targetToSource(hostMonitorPOS);
        return PageUtils.build(pageInfo, configs);
    }
}
