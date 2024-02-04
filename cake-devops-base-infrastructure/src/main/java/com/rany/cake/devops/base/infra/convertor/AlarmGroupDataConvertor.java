package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.entity.AlarmGroup;
import com.rany.cake.devops.base.domain.entity.AlarmGroupNotify;
import com.rany.cake.devops.base.domain.entity.AlarmGroupUser;
import com.rany.cake.devops.base.infra.po.AlarmGroupNotifyPO;
import com.rany.cake.devops.base.infra.po.AlarmGroupPO;
import com.rany.cake.devops.base.infra.po.AlarmGroupUserPO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 告警组
 *
 * @author zhongshengwang
 * @description 告警组
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface AlarmGroupDataConvertor extends BaseConvertor<AlarmGroup, AlarmGroupPO> {


    /**
     * 转PO
     *
     * @param alarmGroup 告警组
     * @return PO
     */
    @Override
    AlarmGroupPO sourceToTarget(AlarmGroup alarmGroup);

    AlarmGroupNotifyPO convertNotify(AlarmGroupNotify alarmGroupNotify);

    List<AlarmGroupNotifyPO> convertNotify(List<AlarmGroupNotify> alarmGroupNotify);

    AlarmGroupUserPO convertUser(AlarmGroupUser alarmGroupUser);

    List<AlarmGroupUserPO> convertUser(List<AlarmGroupUser> alarmGroupUser);

    /**
     * 转实体
     *
     * @param alarmGroupPO 告警组
     * @return 实体
     */

    @Override
    AlarmGroup targetToSource(AlarmGroupPO alarmGroupPO);

    AlarmGroupNotify reConvertNotify(AlarmGroupNotifyPO alarmGroupNotifyPO);


    List<AlarmGroupNotify> reConvertNotify(List<AlarmGroupNotifyPO> alarmGroupNotifyPO);

    AlarmGroupUser reConvertUser(AlarmGroupUserPO alarmGroupUserPO);


    List<AlarmGroupUser> reConvertUser(List<AlarmGroupUserPO> alarmGroupUserPO);
}
