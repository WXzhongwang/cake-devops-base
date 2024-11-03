package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.DeployHistoryDTO;
import com.rany.cake.devops.base.api.query.release.DeployHistoryPageQuery;
import com.rany.cake.devops.base.domain.entity.DeployHistory;
import com.rany.cake.devops.base.domain.repository.param.DeployHistoryPageParam;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeployHistoryDataAdapter extends BaseConvertor<DeployHistory, DeployHistoryDTO> {
    DeployHistoryPageParam convertParam(DeployHistoryPageQuery pageQuery);
}
