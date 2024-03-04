package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.entity.HostEnv;
import com.rany.cake.devops.base.infra.po.HostEnvPO;
import org.mapstruct.Mapper;

/**
 * 主机环境变量
 *
 * @author zhongshengwang
 * @description 主机环境变量
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface HostEnvDataConvertor extends BaseConvertor<HostEnv, HostEnvPO> {


}
