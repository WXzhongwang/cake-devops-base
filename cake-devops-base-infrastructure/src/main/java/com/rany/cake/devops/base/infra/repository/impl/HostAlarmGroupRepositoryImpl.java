package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.entity.HostAlarmGroup;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.HostAlarmGroupRepository;
import com.rany.cake.devops.base.infra.convertor.HostAlarmGroupDataConvertor;
import com.rany.cake.devops.base.infra.mapper.HostAlarmGroupPOMapper;
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

    @Override
    public List<HostAlarmGroup> find(HostId hostId) {
        return null;
    }

    @Override
    public void save(HostAlarmGroup alarmGroup) {

    }

    @Override
    public void save(List<HostAlarmGroup> alarmGroupList) {

    }

    @Override
    public void update(HostAlarmGroup env) {

    }
}
