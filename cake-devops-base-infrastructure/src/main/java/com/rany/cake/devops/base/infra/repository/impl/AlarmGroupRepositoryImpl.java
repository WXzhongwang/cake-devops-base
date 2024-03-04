package com.rany.cake.devops.base.infra.repository.impl;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.cake.devops.base.domain.entity.AlarmGroup;
import com.rany.cake.devops.base.domain.entity.AlarmGroupNotify;
import com.rany.cake.devops.base.domain.entity.AlarmGroupUser;
import com.rany.cake.devops.base.domain.repository.AlarmGroupRepository;
import com.rany.cake.devops.base.domain.repository.param.AlarmGroupQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.infra.convertor.AlarmGroupDataConvertor;
import com.rany.cake.devops.base.infra.dao.AlarmGroupDao;
import com.rany.cake.devops.base.infra.mapper.AlarmGroupNotifyPOMapper;
import com.rany.cake.devops.base.infra.mapper.AlarmGroupPOMapper;
import com.rany.cake.devops.base.infra.mapper.AlarmGroupUserPOMapper;
import com.rany.cake.devops.base.infra.po.AlarmGroupNotifyPO;
import com.rany.cake.devops.base.infra.po.AlarmGroupPO;
import com.rany.cake.devops.base.infra.po.AlarmGroupUserPO;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class AlarmGroupRepositoryImpl implements AlarmGroupRepository {
    private AlarmGroupPOMapper alarmGroupPOMapper;
    private AlarmGroupDao alarmGroupDao;
    private AlarmGroupNotifyPOMapper alarmGroupNotifyPOMapper;
    private AlarmGroupUserPOMapper alarmGroupUserPOMapper;
    private AlarmGroupDataConvertor alarmGroupDataConvertor;

    @Override
    public AlarmGroup find(Long id) {
        AlarmGroupPO alarmGroupPO = alarmGroupPOMapper.selectByPrimaryKey(id);
        AlarmGroup alarmGroup = alarmGroupDataConvertor.targetToSource(alarmGroupPO);

        // 查询用户
        List<AlarmGroupUserPO> alarmGroupUserPOS = alarmGroupDao.selectUsers(alarmGroup.getId());
        List<AlarmGroupUser> alarmGroupUsers = alarmGroupDataConvertor.reConvertUser(alarmGroupUserPOS);
        alarmGroup.setUsers(alarmGroupUsers);

        // 查询通知
        List<AlarmGroupNotifyPO> alarmGroupNotifyPOS = alarmGroupDao.selectNotifies(alarmGroupPO.getId());
        List<AlarmGroupNotify> alarmGroupNotifies = alarmGroupDataConvertor.reConvertNotify(alarmGroupNotifyPOS);
        alarmGroup.setNotifies(alarmGroupNotifies);
        return alarmGroup;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(AlarmGroup group) {
        AlarmGroupPO alarmGroupPO = alarmGroupPOMapper.selectByPrimaryKey(group.getId());
        alarmGroupPO.setIsDeleted(DeleteStatusEnum.YES.getValue());
        alarmGroupPOMapper.updateByPrimaryKey(alarmGroupPO);

        alarmGroupDao.deleteUsers(alarmGroupPO.getId());
        alarmGroupDao.deleteNotifies(alarmGroupPO.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(AlarmGroup group) {
        AlarmGroupPO alarmGroupPO = alarmGroupDataConvertor.sourceToTarget(group);
        alarmGroupPOMapper.insertSelective(alarmGroupPO);

        group.getUsers().forEach(p -> {
            p.setGroupId(alarmGroupPO.getId());
            p.setGmtCreate(alarmGroupPO.getGmtCreate());
        });
        List<AlarmGroupUserPO> alarmGroupUserPOS = alarmGroupDataConvertor.convertUser(group.getUsers());
        alarmGroupDao.batchSaveUser(alarmGroupUserPOS);

        group.getNotifies().forEach(p -> {
            p.setGroupId(alarmGroupPO.getId());
            p.setGmtCreate(alarmGroupPO.getGmtCreate());
        });
        List<AlarmGroupNotifyPO> alarmGroupNotifyPOS = alarmGroupDataConvertor.convertNotify(group.getNotifies());
        alarmGroupDao.batchSaveNotify(alarmGroupNotifyPOS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AlarmGroup group) {
        AlarmGroupPO alarmGroupPO = alarmGroupDataConvertor.sourceToTarget(group);
        alarmGroupPOMapper.updateByPrimaryKeySelective(alarmGroupPO);

        alarmGroupDao.deleteUsers(alarmGroupPO.getId());
        alarmGroupDao.deleteNotifies(alarmGroupPO.getId());

        group.getUsers().forEach(p -> {
            p.setGroupId(alarmGroupPO.getId());
            p.setGmtCreate(alarmGroupPO.getGmtCreate());
        });
        List<AlarmGroupUserPO> alarmGroupUserPOS = alarmGroupDataConvertor.convertUser(group.getUsers());
        alarmGroupDao.batchSaveUser(alarmGroupUserPOS);

        group.getNotifies().forEach(p -> {
            p.setGroupId(alarmGroupPO.getId());
            p.setGmtCreate(alarmGroupPO.getGmtCreate());
        });
        List<AlarmGroupNotifyPO> alarmGroupNotifyPOS = alarmGroupDataConvertor.convertNotify(group.getNotifies());
        alarmGroupDao.batchSaveNotify(alarmGroupNotifyPOS);
    }

    @Override
    public Page<AlarmGroup> page(AlarmGroupQueryParam queryParam) {
        List<AlarmGroupPO> alarmGroupPOS = alarmGroupDao.queryAlarmGroup(queryParam);
        PageInfo<AlarmGroupPO> pageInfo = new PageInfo<>(alarmGroupPOS);
        List<AlarmGroup> configs = alarmGroupDataConvertor.targetToSource(alarmGroupPOS);
        return PageUtils.build(pageInfo, configs);
    }
}
