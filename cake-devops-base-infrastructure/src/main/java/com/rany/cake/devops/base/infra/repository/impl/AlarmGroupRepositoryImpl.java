package com.rany.cake.devops.base.infra.repository.impl;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.cake.devops.base.domain.entity.AlarmGroup;
import com.rany.cake.devops.base.domain.entity.AlarmGroupNotify;
import com.rany.cake.devops.base.domain.entity.AlarmGroupUser;
import com.rany.cake.devops.base.domain.repository.AlarmGroupRepository;
import com.rany.cake.devops.base.domain.repository.param.AlarmGroupQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.infra.aop.PagingQuery;
import com.rany.cake.devops.base.infra.convertor.AlarmGroupDataConvertor;
import com.rany.cake.devops.base.infra.dao.AlarmGroupDao;
import com.rany.cake.devops.base.infra.mapper.AlarmGroupNotifyPOMapper;
import com.rany.cake.devops.base.infra.mapper.AlarmGroupPOMapper;
import com.rany.cake.devops.base.infra.mapper.AlarmGroupUserPOMapper;
import com.rany.cake.devops.base.infra.po.AlarmGroupNotifyPO;
import com.rany.cake.devops.base.infra.po.AlarmGroupPO;
import com.rany.cake.devops.base.infra.po.AlarmGroupUserPO;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Slf4j
@Service
public class AlarmGroupRepositoryImpl implements AlarmGroupRepository {
    private final AlarmGroupPOMapper alarmGroupPOMapper;
    private final AlarmGroupDao alarmGroupDao;
    private final AlarmGroupNotifyPOMapper alarmGroupNotifyPOMapper;
    private final AlarmGroupUserPOMapper alarmGroupUserPOMapper;
    private final AlarmGroupDataConvertor alarmGroupDataConvertor;

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
    public List<AlarmGroup> findByGroupIds(List<Long> groupIds) {
        List<AlarmGroup> alarmGroupList = new ArrayList<>();
        for (Long groupId : groupIds) {
            AlarmGroup alarmGroup = this.find(groupId);
            alarmGroupList.add(alarmGroup);
        }
        return alarmGroupList;
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
        alarmGroupDao.save(group);

        group.getUsers().forEach(p -> {
            p.setGroupId(group.getId());
            p.setGmtCreate(group.getGmtCreate());
            p.setCreator(group.getCreator());
        });
        List<AlarmGroupUserPO> alarmGroupUserPOS = alarmGroupDataConvertor.convertUser(group.getUsers());
        alarmGroupDao.batchSaveUser(alarmGroupUserPOS);

        group.getNotifies().forEach(p -> {
            p.setGroupId(group.getId());
            p.setGmtCreate(group.getGmtCreate());
            p.setCreator(group.getCreator());
        });
        List<AlarmGroupNotifyPO> alarmGroupNotifyPOS = alarmGroupDataConvertor.convertNotify(group.getNotifies());
        alarmGroupDao.batchSaveNotify(alarmGroupNotifyPOS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AlarmGroup group) {
        AlarmGroupPO alarmGroupPO = alarmGroupDataConvertor.sourceToTarget(group);
        alarmGroupPOMapper.updateByPrimaryKey(alarmGroupPO);

        alarmGroupDao.deleteUsers(alarmGroupPO.getId());
        alarmGroupDao.deleteNotifies(alarmGroupPO.getId());

        group.getUsers().forEach(p -> {
            p.setGroupId(alarmGroupPO.getId());
            p.setGmtCreate(alarmGroupPO.getGmtCreate());
            p.setCreator(alarmGroupPO.getCreator());
        });
        List<AlarmGroupUserPO> alarmGroupUserPOS = alarmGroupDataConvertor.convertUser(group.getUsers());
        alarmGroupDao.batchSaveUser(alarmGroupUserPOS);

        group.getNotifies().forEach(p -> {
            p.setGroupId(alarmGroupPO.getId());
            p.setGmtCreate(alarmGroupPO.getGmtCreate());
            p.setCreator(alarmGroupPO.getCreator());
        });
        List<AlarmGroupNotifyPO> alarmGroupNotifyPOS = alarmGroupDataConvertor.convertNotify(group.getNotifies());
        alarmGroupDao.batchSaveNotify(alarmGroupNotifyPOS);
    }

    @Override
    @PagingQuery
    public Page<AlarmGroup> page(AlarmGroupQueryParam queryParam) {
        List<AlarmGroupPO> alarmGroupPOS = alarmGroupDao.queryAlarmGroup(queryParam);
        PageInfo<AlarmGroupPO> pageInfo = new PageInfo<>(alarmGroupPOS);
        List<AlarmGroup> configs = alarmGroupDataConvertor.targetToSource(alarmGroupPOS);
        for (AlarmGroup config : configs) {
            // 查询用户
            List<AlarmGroupUserPO> alarmGroupUserPOS = alarmGroupDao.selectUsers(config.getId());
            List<AlarmGroupUser> alarmGroupUsers = alarmGroupDataConvertor.reConvertUser(alarmGroupUserPOS);
            config.setUsers(alarmGroupUsers);

            // 查询通知
            List<AlarmGroupNotifyPO> alarmGroupNotifyPOS = alarmGroupDao.selectNotifies(config.getId());
            List<AlarmGroupNotify> alarmGroupNotifies = alarmGroupDataConvertor.reConvertNotify(alarmGroupNotifyPOS);
            config.setNotifies(alarmGroupNotifies);
        }
        return PageUtils.build(pageInfo, configs);
    }
}
