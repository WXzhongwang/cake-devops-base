package com.rany.cake.devops.base.domain.repository;

import com.rany.cake.devops.base.domain.entity.HostAlarmGroup;
import com.rany.cake.devops.base.domain.pk.HostId;

import java.util.List;

/**
 * 主机告警组配置
 */
public interface HostAlarmGroupRepository {
    List<HostAlarmGroup> find(HostId hostId);

    int delete(String hostId);

    void save(HostAlarmGroup alarmGroup);

    void save(List<HostAlarmGroup> alarmGroupList);

    void update(HostAlarmGroup env);
}
