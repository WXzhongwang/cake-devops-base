package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.AppDTO;
import com.rany.cake.devops.base.api.dto.AppEnvDTO;
import com.rany.cake.devops.base.api.dto.ResourceStrategyDTO;
import com.rany.cake.devops.base.api.query.app.AppPageQuery;
import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.valueobject.ResourceStrategy;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
import com.rany.cake.devops.base.domain.repository.param.AppQueryParam;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * 应用
 *
 * @author zhongshengwang
 * @description 应用
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface AppDataAdapter extends BaseConvertor<App, AppDTO> {


    /**
     * 聚合根转PO
     *
     * @param app 聚合根
     * @return PO
     */
    @Mapping(source = "appId.appId", target = "appId")
    @Mapping(source = "appName.name", target = "appName")
    @Mapping(source = "codeRepository.repo", target = "repo")
    @Mapping(source = "codeRepository.defaultBranch", target = "defaultBranch")
    @Mapping(source = "businessOwnership.departmentAbbreviation", target = "departmentAbbreviation")
    @Mapping(source = "businessOwnership.department", target = "department")
    @Mapping(source = "language", target = "language")
    @Mapping(source = "developMode", target = "developMode")
    AppDTO sourceToTarget(App app);


    @Mapping(target = "envId", source = "envId")
    @Mapping(source = "appId.appId", target = "appId")
    @Mapping(source = "clusterId.clusterId", target = "clusterId")
    @Mapping(source = "env", target = "env")
    @Mapping(target = "resourceStrategy", expression = "java(this.strategySourceToTarget(appEnv.getResourceStrategy()))")
    AppEnvDTO envSourceToTarget(AppEnv appEnv);

    @InheritConfiguration(name = "envSourceToTarget")
    List<AppEnvDTO> envSourceToTarget(List<AppEnv> appEnvs);

    @Mapping(target = "appId.appId", source = "appId")
    @Mapping(target = "envId", source = "envId")
    @Mapping(target = "clusterId.clusterId", source = "clusterId")
    @Mapping(target = "env", source = "env")
    @Mapping(target = "resourceStrategy", expression = "java(this.strategyTargetToSource(appEnvDTO.getResourceStrategy()))")
    AppEnv envTargetToSource(AppEnvDTO appEnvDTO);

    @InheritConfiguration(name = "envTargetToSource")
    List<AppEnv> envTargetToSource(List<AppEnvDTO> appEnvDTOs);

    ResourceStrategyDTO strategySourceToTarget(ResourceStrategy resourceStrategy);

    ResourceStrategy strategyTargetToSource(ResourceStrategyDTO resourceStrategyDTO);

    /**
     * PO转聚合根
     *
     * @param appDTO PO
     * @return 聚合根
     */

    @Mapping(target = "appId.appId", source = "appId")
    @Mapping(target = "appName.name", source = "appName")
    @Mapping(target = "codeRepository.repo", source = "repo")
    @Mapping(target = "codeRepository.defaultBranch", source = "defaultBranch")
    @Mapping(target = "businessOwnership.departmentAbbreviation", source = "departmentAbbreviation")
    @Mapping(target = "businessOwnership.department", source = "department")
    @Mapping(target = "language", source = "language")
    @Mapping(target = "developMode", source = "developMode")
    App targetToSource(AppDTO appDTO);


    AppQueryParam convertParam(AppPageQuery appPageQuery);
}
