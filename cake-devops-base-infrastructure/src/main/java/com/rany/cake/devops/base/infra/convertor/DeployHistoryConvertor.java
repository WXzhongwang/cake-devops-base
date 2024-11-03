package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.entity.DeployHistory;
import com.rany.cake.devops.base.infra.po.DeployHistoryPO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeployHistoryConvertor extends BaseConvertor<DeployHistory, DeployHistoryPO> {
}
