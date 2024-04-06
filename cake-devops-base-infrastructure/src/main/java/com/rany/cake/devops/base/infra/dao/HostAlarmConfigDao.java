package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.entity.HostAlarmConfig;
import com.rany.cake.devops.base.infra.po.HostAlarmConfigPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HostAlarmConfigDao {

    List<HostAlarmConfigPO> queryHostAlarmConfig(String hostId);

    int deleteByHostId(@Param("hostId") String hostId);

    int delete(@Param("hostId") String hostId, @Param("alarmType") Integer alarmType);

    HostAlarmConfigPO selectHostAlarmConfig(@Param("hostId") String hostId, @Param("alarmType") Integer alarmType);

    int save(HostAlarmConfig config);

    int update(HostAlarmConfig config);

}
