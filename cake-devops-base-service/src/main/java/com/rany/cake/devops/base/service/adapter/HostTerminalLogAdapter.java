package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.HostTerminalLogDTO;
import com.rany.cake.devops.base.api.query.terminal.TerminalAccessLogPageQuery;
import com.rany.cake.devops.base.domain.entity.HostTerminalLog;
import com.rany.cake.devops.base.domain.repository.param.TerminalLogPageQueryParam;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HostTerminalLogAdapter extends BaseConvertor<HostTerminalLog, HostTerminalLogDTO> {


    TerminalLogPageQueryParam convertToParam(TerminalAccessLogPageQuery terminalAccessLogPageQuery);
}
