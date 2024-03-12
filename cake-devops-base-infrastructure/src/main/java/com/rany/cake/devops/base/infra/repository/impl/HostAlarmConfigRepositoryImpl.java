package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.entity.HostAlarmConfig;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.HostAlarmConfigRepository;
import com.rany.cake.devops.base.infra.convertor.HostAlarmConfigDataConvertor;
import com.rany.cake.devops.base.infra.dao.HostAlarmConfigDao;
import com.rany.cake.devops.base.infra.mapper.HostAlarmConfigPOMapper;
import com.rany.cake.devops.base.infra.po.HostAlarmConfigPO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class HostAlarmConfigRepositoryImpl implements HostAlarmConfigRepository {
    private final HostAlarmConfigPOMapper hostAlarmConfigPOMapper;
    private final HostAlarmConfigDataConvertor hostAlarmConfigDataConvertor;
    private final HostAlarmConfigDao hostAlarmConfigDao;

    @Override
    public List<HostAlarmConfig> find(HostId hostId) {
        List<HostAlarmConfigPO> hostAlarmConfigPOS = hostAlarmConfigDao.queryHostAlarmConfig(hostId.getHostId());
        return hostAlarmConfigDataConvertor.targetToSource(hostAlarmConfigPOS);
    }

    @Override
    public void deleteAlarmConfig(String hostId, Integer alarmType) {
        hostAlarmConfigDao.delete(hostId, alarmType);
    }

    @Override
    public void deleteAlarmConfig(String hostId) {
        hostAlarmConfigDao.deleteByHostId(hostId);
    }

    @Override
    public void save(HostAlarmConfig alarmConfig) {
        hostAlarmConfigDao.save(alarmConfig);
    }

    @Override
    public void update(HostAlarmConfig alarmConfig) {
        hostAlarmConfigDao.update(alarmConfig);
    }
}
