package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.entity.HostAlarmHistory;
import com.rany.cake.devops.base.domain.repository.param.HostAlarmHistoryPageQueryParam;

import java.util.List;

/**
 * 主机告警历史配置
 */
public interface HostAlarmHistoryRepository {
    List<HostAlarmHistory> queryAlarmHistory(HostAlarmHistoryPageQueryParam hostAlarmHistoryPageQueryParam);

    Page<HostAlarmHistory> pageQueryAlarmHistory(HostAlarmHistoryPageQueryParam hostAlarmHistoryPageQueryParam);

    void save(HostAlarmHistory hostAlarmHistory);

    void batchSave(List<HostAlarmHistory> hostAlarmHistory);
}
