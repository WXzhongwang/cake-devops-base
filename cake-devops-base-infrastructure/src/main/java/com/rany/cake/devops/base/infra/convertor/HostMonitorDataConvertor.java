package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.entity.HostMonitor;
import com.rany.cake.devops.base.infra.po.HostMonitorPO;
import org.mapstruct.Mapper;

/**
 * 主机监控配置
 *
 * @author zhongshengwang
 * @description 主机监控配置
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface HostMonitorDataConvertor extends BaseConvertor<HostMonitor, HostMonitorPO> {


}
