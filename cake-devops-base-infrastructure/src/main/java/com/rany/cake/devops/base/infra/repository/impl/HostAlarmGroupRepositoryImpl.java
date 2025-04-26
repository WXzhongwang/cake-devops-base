package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.entity.HostAlarmGroup;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.HostAlarmGroupRepository;
import com.rany.cake.devops.base.infra.convertor.HostAlarmGroupDataConvertor;
import com.rany.cake.devops.base.infra.dao.HostAlarmGroupDao;
import com.rany.cake.devops.base.infra.mapper.HostAlarmGroupPOMapper;
import com.rany.cake.devops.base.infra.po.HostAlarmGroupPO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class HostAlarmGroupRepositoryImpl implements HostAlarmGroupRepository {

    private final HostAlarmGroupPOMapper hostAlarmGroupPOMapper;
    private final HostAlarmGroupDataConvertor hostAlarmGroupDataConvertor;
    private final HostAlarmGroupDao hostAlarmGroupDao;

    @Override
    public List<HostAlarmGroup> find(HostId hostId) {
        List<HostAlarmGroupPO> hostAlarmGroupPOS = hostAlarmGroupDao.queryHostAlarmGroup(hostId.getHostId());
        return hostAlarmGroupDataConvertor.targetToSource(hostAlarmGroupPOS);
    }

    @Override
    public int delete(String hostId) {
        return hostAlarmGroupDao.deleteByHostId(hostId);
    }

    @Override
    public void save(HostAlarmGroup alarmGroup) {
        HostAlarmGroupPO hostAlarmGroupPO = hostAlarmGroupDataConvertor.sourceToTarget(alarmGroup);
        hostAlarmGroupPOMapper.insert(hostAlarmGroupPO);
    }

    @Override
    public void save(List<HostAlarmGroup> alarmGroupList) {
        List<HostAlarmGroupPO> hostAlarmGroupPOS = hostAlarmGroupDataConvertor.sourceToTarget(alarmGroupList);
        for (HostAlarmGroupPO hostAlarmGroupPO : hostAlarmGroupPOS) {
            hostAlarmGroupPOMapper.insert(hostAlarmGroupPO);
        }
    }

    @Override
    public void update(HostAlarmGroup alarmGroup) {
        HostAlarmGroupPO hostAlarmGroupPO = hostAlarmGroupDataConvertor.sourceToTarget(alarmGroup);
        hostAlarmGroupPOMapper.updateByPrimaryKey(hostAlarmGroupPO);
    }
}
