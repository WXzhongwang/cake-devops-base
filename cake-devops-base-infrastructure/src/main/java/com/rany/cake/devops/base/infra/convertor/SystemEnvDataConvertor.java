package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.entity.SystemEnv;
import com.rany.cake.devops.base.infra.po.SystemEnvPO;
import org.mapstruct.Mapper;

/**
 * 环境变量
 *
 * @author zhongshengwang
 * @description 环境变量
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface SystemEnvDataConvertor extends BaseConvertor<SystemEnv, SystemEnvPO> {


}
