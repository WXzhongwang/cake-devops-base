package com.rany.cake.devops.base.infra.repository.impl;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.cake.devops.base.domain.entity.UserEventLog;
import com.rany.cake.devops.base.domain.repository.UserEventLogRepository;
import com.rany.cake.devops.base.domain.repository.param.UserEventLogQueryPageParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.infra.aop.PagingQuery;
import com.rany.cake.devops.base.infra.convertor.UserEventLogDataConvertor;
import com.rany.cake.devops.base.infra.dao.UserEventLogDao;
import com.rany.cake.devops.base.infra.mapper.UserEventLogPOMapper;
import com.rany.cake.devops.base.infra.po.UserEventLogPO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserEventLogRepositoryImpl implements UserEventLogRepository {
    private final UserEventLogPOMapper userEventLogPOMapper;
    private final UserEventLogDao userEventLogDao;
    private final UserEventLogDataConvertor userEventLogDataConvertor;

    @Override
    public UserEventLog find(Long id) {
        UserEventLogPO userEventLogPO = userEventLogPOMapper.selectByPrimaryKey(id);
        return userEventLogDataConvertor.targetToSource(userEventLogPO);
    }

    @Override
    public void save(UserEventLog log) {
        userEventLogDao.save(log);
    }

    @Override
    public int update(UserEventLog log) {
        UserEventLogPO userEventLogPO = userEventLogDataConvertor.sourceToTarget(log);
        return userEventLogPOMapper.updateByPrimaryKey(userEventLogPO);
    }

    @Override
    @PagingQuery
    public Page<UserEventLog> pageQuery(UserEventLogQueryPageParam param) {
        List<UserEventLogPO> userEventLogPOList = userEventLogDao.selectList(param);
        PageInfo<UserEventLogPO> pageInfo = new PageInfo<>(userEventLogPOList);
        List<UserEventLog> userEventLogs = userEventLogDataConvertor.targetToSource(userEventLogPOList);
        return PageUtils.build(pageInfo, userEventLogs);
    }
}
