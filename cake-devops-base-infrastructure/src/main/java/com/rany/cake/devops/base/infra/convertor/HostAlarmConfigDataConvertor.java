package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.entity.HostAlarmConfig;
import com.rany.cake.devops.base.infra.po.HostAlarmConfigPO;
import org.mapstruct.Mapper;

/**
 * 主机告警配置
 *
 * @author zhongshengwang
 * @description 主机告警配置
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface HostAlarmConfigDataConvertor extends BaseConvertor<HostAlarmConfig, HostAlarmConfigPO> {


}
