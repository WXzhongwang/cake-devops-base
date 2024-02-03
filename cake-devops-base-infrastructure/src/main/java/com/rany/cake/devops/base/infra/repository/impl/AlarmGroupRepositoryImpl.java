package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.entity.AlarmGroup;
import com.rany.cake.devops.base.domain.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.domain.repository.AlarmGroupRepository;
import com.rany.cake.devops.base.infra.convertor.AlarmGroupDataConvertor;
import com.rany.cake.devops.base.infra.mapper.AlarmGroupNotifyPOMapper;
import com.rany.cake.devops.base.infra.mapper.AlarmGroupPOMapper;
import com.rany.cake.devops.base.infra.mapper.AlarmGroupUserPOMapper;
import com.rany.cake.devops.base.infra.po.AlarmGroupPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AlarmGroupRepositoryImpl implements AlarmGroupRepository {
    private AlarmGroupPOMapper alarmGroupPOMapper;
    private AlarmGroupNotifyPOMapper alarmGroupNotifyPOMapper;
    private AlarmGroupUserPOMapper alarmGroupUserPOMapper;
    private AlarmGroupDataConvertor alarmGroupDataConvertor;

    @Override
    public AlarmGroup find(Long id) {
        AlarmGroupPO alarmGroupPO = alarmGroupPOMapper.selectByPrimaryKey(id);
        return alarmGroupDataConvertor.targetToSource(alarmGroupPO);
    }

    @Override
    public void remove(AlarmGroup group) {
        AlarmGroupPO alarmGroupPO = alarmGroupPOMapper.selectByPrimaryKey(group.getId());
        alarmGroupPO.setIsDeleted(DeleteStatusEnum.YES.getValue());
        alarmGroupPOMapper.updateByPrimaryKey(alarmGroupPO);
    }

    @Override
    public void save(AlarmGroup group) {
        AlarmGroupPO alarmGroupPO = alarmGroupDataConvertor.sourceToTarget(group);
        alarmGroupPOMapper.insertSelective(alarmGroupPO);
    }
}
