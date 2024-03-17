package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.HostMonitorDTO;
import com.rany.cake.devops.base.api.query.HostMonitorPageQuery;
import com.rany.cake.devops.base.domain.entity.HostMonitor;
import com.rany.cake.devops.base.domain.repository.param.HostMonitorPageQueryParam;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
import org.mapstruct.Mapper;

/**
 * 主机监控
 *
 * @author zhongshengwang
 * @description 主机监控
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface HostMonitorDataAdapter extends BaseConvertor<HostMonitor, HostMonitorDTO> {

    HostMonitorPageQueryParam convertParam(HostMonitorPageQuery hostMonitorPageQuery);
}
