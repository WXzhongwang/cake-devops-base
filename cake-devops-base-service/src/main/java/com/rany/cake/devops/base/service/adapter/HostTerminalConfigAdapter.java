package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.HostTerminalConfigDTO;
import com.rany.cake.devops.base.domain.entity.HostTerminalConfig;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HostTerminalConfigAdapter extends BaseConvertor<HostTerminalConfig, HostTerminalConfigDTO> {
}
