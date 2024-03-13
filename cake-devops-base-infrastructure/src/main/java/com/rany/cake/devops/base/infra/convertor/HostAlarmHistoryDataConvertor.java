package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.entity.HostAlarmHistory;
import com.rany.cake.devops.base.infra.po.HostAlarmHistoryPO;
import org.mapstruct.Mapper;

/**
 * 主机告警历史
 *
 * @author zhongshengwang
 * @description 主机告警历史
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface HostAlarmHistoryDataConvertor extends BaseConvertor<HostAlarmHistory, HostAlarmHistoryPO> {


}
