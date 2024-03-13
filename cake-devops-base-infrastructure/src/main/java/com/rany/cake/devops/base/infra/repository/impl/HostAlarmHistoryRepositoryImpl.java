package com.rany.cake.devops.base.infra.repository.impl;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.cake.devops.base.domain.entity.HostAlarmHistory;
import com.rany.cake.devops.base.domain.repository.HostAlarmHistoryRepository;
import com.rany.cake.devops.base.domain.repository.param.HostAlarmHistoryPageQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.infra.aop.PagingQuery;
import com.rany.cake.devops.base.infra.convertor.HostAlarmHistoryDataConvertor;
import com.rany.cake.devops.base.infra.dao.HostAlarmHistoryDao;
import com.rany.cake.devops.base.infra.mapper.HostAlarmHistoryPOMapper;
import com.rany.cake.devops.base.infra.po.HostAlarmHistoryPO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class HostAlarmHistoryRepositoryImpl implements HostAlarmHistoryRepository {
    private final HostAlarmHistoryPOMapper hostAlarmHistoryPOMapper;
    private final HostAlarmHistoryDao historyDao;
    private final HostAlarmHistoryDataConvertor historyDataConvertor;

    @Override
    public List<HostAlarmHistory> queryAlarmHistory(HostAlarmHistoryPageQueryParam hostAlarmHistoryPageQueryParam) {
        List<HostAlarmHistoryPO> hostAlarmHistoryPOS = historyDao.queryHostAlarmHistory(hostAlarmHistoryPageQueryParam);
        return historyDataConvertor.targetToSource(hostAlarmHistoryPOS);
    }

    @Override
    @PagingQuery
    public Page<HostAlarmHistory> pageQueryAlarmHistory(HostAlarmHistoryPageQueryParam hostAlarmHistoryPageQueryParam) {
        List<HostAlarmHistoryPO> hostAlarmHistoryPOS = historyDao.queryHostAlarmHistory(hostAlarmHistoryPageQueryParam);
        PageInfo<HostAlarmHistoryPO> pageInfo = new PageInfo<>(hostAlarmHistoryPOS);
        List<HostAlarmHistory> hostAlarmHistories = historyDataConvertor.targetToSource(hostAlarmHistoryPOS);
        return PageUtils.build(pageInfo, hostAlarmHistories);
    }

    @Override
    public void save(HostAlarmHistory hostAlarmHistory) {
        historyDao.save(hostAlarmHistory);
    }

    @Override
    public void batchSave(List<HostAlarmHistory> hostAlarmHistory) {
        for (HostAlarmHistory alarmHistory : hostAlarmHistory) {
            historyDao.save(alarmHistory);
        }
    }
}
