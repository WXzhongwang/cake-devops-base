package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.entity.HostAlarmGroup;
import com.rany.cake.devops.base.infra.po.HostAlarmGroupPO;

import java.util.List;

public interface HostAlarmGroupDao {

    List<HostAlarmGroupPO> queryHostAlarmGroup(String hostId);

    int deleteByHostId(String hostId);

    int save(HostAlarmGroup config);

    int update(HostAlarmGroup config);

}
