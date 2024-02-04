package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.repository.param.AlarmGroupQueryParam;
import com.rany.cake.devops.base.infra.po.AlarmGroupNotifyPO;
import com.rany.cake.devops.base.infra.po.AlarmGroupPO;
import com.rany.cake.devops.base.infra.po.AlarmGroupUserPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlarmGroupDao {

    /**
     * 查询模版
     *
     * @param alarmGroupQueryParam 模版查询
     * @return 模版查询结果
     */
    List<AlarmGroupPO> queryAlarmGroup(AlarmGroupQueryParam alarmGroupQueryParam);

    int batchSaveUser(@Param("users") List<AlarmGroupUserPO> users);

    int deleteNotifies(@Param("groupId") Long groupId);

    List<AlarmGroupNotifyPO> selectNotifies(@Param("groupId") Long groupId);

    int deleteUsers(@Param("groupId") Long groupId);

    List<AlarmGroupUserPO> selectUsers(@Param("groupId") Long groupId);

    int batchSaveNotify(@Param("notifies") List<AlarmGroupNotifyPO> users);
}