package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.entity.HostAlarmHistory;
import com.rany.cake.devops.base.infra.po.HostAlarmHistoryPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HostAlarmHistoryDao {

    List<HostAlarmHistoryPO> queryHostAlarmHistory(String hostId, Integer alarmType);

    int deleteByHostId(@Param("hostId") String hostId);

    int delete(@Param("hostId") String hostId, @Param("alarmType") Integer alarmType);

    int save(HostAlarmHistory config);

}
