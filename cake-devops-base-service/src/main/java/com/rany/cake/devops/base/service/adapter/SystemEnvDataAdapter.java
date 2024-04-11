package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.SystemEnvDTO;
import com.rany.cake.devops.base.api.query.system.SystemEnvPageQuery;
import com.rany.cake.devops.base.domain.entity.SystemEnv;
import com.rany.cake.devops.base.domain.repository.param.SystemEnvPageQueryParam;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
import org.mapstruct.Mapper;

/**
 * webhook
 *
 * @author zhongshengwang
 * @description webhook
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface SystemEnvDataAdapter extends BaseConvertor<SystemEnv, SystemEnvDTO> {

    SystemEnvPageQueryParam convertParam(SystemEnvPageQuery systemEnvPageQuery);
}
