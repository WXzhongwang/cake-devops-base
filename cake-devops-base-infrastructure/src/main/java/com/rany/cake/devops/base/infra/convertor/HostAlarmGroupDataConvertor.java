package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.entity.HostAlarmGroup;
import com.rany.cake.devops.base.infra.po.HostAlarmGroupPO;
import org.mapstruct.Mapper;

/**
 * 主机告警组
 *
 * @author zhongshengwang
 * @description 主机告警组
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface HostAlarmGroupDataConvertor extends BaseConvertor<HostAlarmGroup, HostAlarmGroupPO> {


}
