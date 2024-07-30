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
    @Mapping(source = "appId.appId", target = "appId")
    @Mapping(source = "clusterId.clusterId", target = "clusterId")
    @Mapping(expression = "java(this.convertString(appEnv.getDomains()))", target = "domains")
    @Mapping(expression = "java(this.convertMapToString(appEnv.getConfigMap()))", target = "configMap")
    @Mapping(source = "resourceStrategy.replicas", target = "replicas")
    @Mapping(source = "resourceStrategy.cpu", target = "cpu")
    @Mapping(source = "resourceStrategy.memory", target = "memory")
    @Mapping(source = "resourceStrategy.maxCpu", target = "maxCpu")
    @Mapping(source = "resourceStrategy.maxMemory", target = "maxMemory")
    @Override
    AppEnvPO sourceToTarget(AppEnv appEnv);

    /**
     * PO转聚合根
     *
     * @param appEnvPO PO
     * @return 聚合根
     */

    @Override
    @Mapping(target = "appId.appId", source = "appId")
    @Mapping(target = "clusterId.clusterId", source = "clusterId")
    @Mapping(target = "domains", expression = "java(this.convertList(appEnvPO.getDomains()))")
    @Mapping(target = "configMap", expression = "java(this.convertStringToMap(appEnvPO.getConfigMap()))")
    @Mapping(target = "resourceStrategy.replicas", source = "replicas")
    @Mapping(target = "resourceStrategy.cpu", source = "cpu")
    @Mapping(target = "resourceStrategy.memory", source = "memory")
    @Mapping(target = "resourceStrategy.maxCpu", source = "maxCpu")
    @Mapping(target = "resourceStrategy.maxMemory", source = "maxMemory")
    AppEnv targetToSource(AppEnvPO appEnvPO);
}
