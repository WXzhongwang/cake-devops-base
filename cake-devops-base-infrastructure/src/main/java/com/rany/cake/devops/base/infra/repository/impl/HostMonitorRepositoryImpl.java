package com.rany.cake.devops.base.infra.repository.impl;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.cake.devops.base.domain.base.MonitorConst;
import com.rany.cake.devops.base.domain.entity.HostMonitor;
import com.rany.cake.devops.base.domain.repository.HostMonitorRepository;
import com.rany.cake.devops.base.domain.repository.param.HostMonitorPageQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.infra.aop.PagingQuery;
import com.rany.cake.devops.base.infra.convertor.HostMonitorDataConvertor;
import com.rany.cake.devops.base.infra.dao.HostDao;
import com.rany.cake.devops.base.infra.dao.HostMonitorDao;
import com.rany.cake.devops.base.infra.mapper.HostMonitorPOMapper;
import com.rany.cake.devops.base.infra.po.HostMonitorPO;
import com.rany.cake.devops.base.infra.po.HostPO;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.util.enums.MonitorStatus;
import com.rany.cake.toolkit.lang.utils.Strings;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 主机监控配置
 *
 * @author zhongshengwang
 */
@Service
@AllArgsConstructor
public class HostMonitorRepositoryImpl implements HostMonitorRepository {

    private final HostMonitorPOMapper hostMonitorPOMapper;
    private final HostDao hostDao;
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
        if (hostMonitorPO == null) {
            HostPO hostPO = hostDao.selectByHostId(hostId);
            // 不存在则插入
            HostMonitor monitor = new HostMonitor();
            monitor.setHostId(hostId);
            monitor.setMonitorStatus(MonitorStatus.NOT_START.getStatus());
            monitor.setMonitorUrl(Strings.format(MonitorConst.DEFAULT_URL_FORMAT, hostPO.getServerAddr()));
            monitor.setAccessToken(MonitorConst.DEFAULT_ACCESS_TOKEN);
            monitor.setIsDeleted(DeleteStatusEnum.NO.getValue());
            monitor.setAccessToken(MonitorConst.DEFAULT_ACCESS_TOKEN);
            hostMonitorDao.save(monitor);
        }
        return hostMonitorDataConvertor.targetToSource(hostMonitorPO);
    }

    @Override
    public List<HostMonitor> findByHostId(List<String> hostIds) {
        List<HostMonitor> configs = new ArrayList<>();
        for (String hostId : hostIds) {
            HostMonitor monitor = this.findByHostId(hostId);
            configs.add(monitor);
        }
        return configs;
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
