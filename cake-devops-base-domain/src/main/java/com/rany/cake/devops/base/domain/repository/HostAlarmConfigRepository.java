package com.rany.cake.devops.base.domain.repository;

import com.rany.cake.devops.base.domain.entity.HostAlarmConfig;
import com.rany.cake.devops.base.domain.pk.HostId;

import java.util.List;

/**
 * 主机报警配置
 */
public interface HostAlarmConfigRepository {
    List<HostAlarmConfig> find(HostId hostId);

    void deleteAlarmConfig(String hostId, Integer alarmType);

    HostAlarmConfig findAlarmConfig(String hostId, Integer alarmType);

    void deleteAlarmConfig(String hostId);

    void save(HostAlarmConfig alarmConfig);

    void update(HostAlarmConfig alarmConfig);
}
