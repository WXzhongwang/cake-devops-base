package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.log.UserEventLogDTO;
import com.rany.cake.devops.base.api.query.log.UserEventLogPageQuery;
import com.rany.cake.devops.base.domain.entity.UserEventLog;
import com.rany.cake.devops.base.domain.repository.param.UserEventLogQueryPageParam;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserEventLogDataAdapter extends BaseConvertor<UserEventLog, UserEventLogDTO> {

    UserEventLogQueryPageParam convertParam(UserEventLogPageQuery pageQuery);
}