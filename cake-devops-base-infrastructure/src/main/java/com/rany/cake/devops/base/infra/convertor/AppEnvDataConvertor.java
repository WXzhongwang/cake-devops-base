package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.infra.po.AppEnvPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 环境
 *
 * @author zhongshengwang
 * @description 环境
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface AppEnvDataConvertor extends BaseConvertor<AppEnv, AppEnvPO> {


    /**
     * 聚合根转PO
     *
     * @param appEnv 聚合根
     * @return PO
     */
    @Mapping(source = "appId.id", target = "appId")
    @Mapping(source = "clusterId.id", target = "clusterId")
    @Mapping(expression = "java(this.convertString(appEnv.getDomains()))", target = "domains")
    @Override
    AppEnvPO sourceToTarget(AppEnv appEnv);

    /**
     * PO转聚合根
     *
     * @param appEnvPO PO
     * @return 聚合根
     */

    @Override
    @Mapping(target = "appId.id", source = "appId")
    @Mapping(target = "clusterId.id", source = "clusterId")
    @Mapping(target = "domains", expression = "java(this.convertList(appEnvPO.getDomains()))")
    AppEnv targetToSource(AppEnvPO appEnvPO);
}
