package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.HostEnvDTO;
import com.rany.cake.devops.base.api.query.HostEnvPageQuery;
import com.rany.cake.devops.base.domain.entity.HostEnv;
import com.rany.cake.devops.base.domain.repository.param.HostEnvQueryParam;
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
public interface HostEnvDataAdapter extends BaseConvertor<HostEnv, HostEnvDTO> {

    HostEnvQueryParam convertParam(HostEnvPageQuery hostEnvPageQuery);
}
