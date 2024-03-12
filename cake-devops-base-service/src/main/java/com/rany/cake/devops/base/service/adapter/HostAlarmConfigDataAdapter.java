package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.HostAlarmConfigDTO;
import com.rany.cake.devops.base.domain.entity.HostAlarmConfig;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
import org.mapstruct.Mapper;

/**
 * HostAlarmConfig
 *
 * @author zhongshengwang
 * @description HostAlarmConfig
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface HostAlarmConfigDataAdapter extends BaseConvertor<HostAlarmConfig, HostAlarmConfigDTO> {

}
