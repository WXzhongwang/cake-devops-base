package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.entity.HostAlarmConfig;
import com.rany.cake.devops.base.infra.po.HostAlarmConfigPO;

import java.util.List;

public interface HostAlarmConfigDao {
    
    List<HostAlarmConfigPO> queryHostAlarmConfig(String hostId);

    int deleteByHostId(String hostId);

    int save(HostAlarmConfig config);

    int update(HostAlarmConfig config);

}
