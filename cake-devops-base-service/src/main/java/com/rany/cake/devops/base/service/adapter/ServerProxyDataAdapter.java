package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.ServerProxyDTO;
import com.rany.cake.devops.base.api.query.proxy.ServerProxyPageQuery;
import com.rany.cake.devops.base.domain.entity.ServerProxy;
import com.rany.cake.devops.base.domain.repository.param.ServerProxyQueryParam;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
import org.mapstruct.Mapper;

/**
 * 机器代理
 *
 * @author zhongshengwang
 * @description 机器代理
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface ServerProxyDataAdapter extends BaseConvertor<ServerProxy, ServerProxyDTO> {

    ServerProxyQueryParam convertParam(ServerProxyPageQuery serverProxyQueryParam);
}
