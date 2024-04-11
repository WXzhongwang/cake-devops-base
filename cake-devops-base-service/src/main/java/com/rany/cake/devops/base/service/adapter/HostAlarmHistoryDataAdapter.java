package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.HostAlarmHistoryDTO;
import com.rany.cake.devops.base.api.query.alarm.HostAlarmHistoryPageQuery;
import com.rany.cake.devops.base.domain.entity.HostAlarmHistory;
import com.rany.cake.devops.base.domain.repository.param.HostAlarmHistoryPageQueryParam;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
import org.mapstruct.Mapper;

/**
 * HostAlarmHistory
 *
 * @author zhongshengwang
 * @description HostAlarmHistory
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface HostAlarmHistoryDataAdapter extends BaseConvertor<HostAlarmHistory, HostAlarmHistoryDTO> {
    HostAlarmHistoryPageQueryParam convertParam(HostAlarmHistoryPageQuery hostAlarmHistoryPageQuery);
}
