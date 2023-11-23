package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.infra.po.AppPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 应用
 *
 * @author zhongshengwang
 * @description 应用
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface AppDataConvertor extends BaseConvertor<App, AppPO> {


    /**
     * 聚合根转PO
     *
     * @param app 聚合根
     * @return PO
     */
    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "appName.name", target = "appName")
    @Mapping(source = "codeRepository.repo", target = "repo")
    @Mapping(source = "codeRepository.defaultBranch", target = "defaultBranch")
    @Mapping(source = "businessOwnership.departmentAbbreviation", target = "departmentAbbreviation")
    @Mapping(source = "businessOwnership.department", target = "department")
    @Override
    AppPO sourceToTarget(App app);

    /**
     * PO转聚合根
     *
     * @param appPO PO
     * @return 聚合根
     */

    @Override
    @Mapping(target = "id.id", source = "id")
    @Mapping(target = "appName.name", source = "appName")
    @Mapping(target = "codeRepository.repo", source = "repo")
    @Mapping(target = "codeRepository.defaultBranch", source = "defaultBranch")
    @Mapping(target = "businessOwnership.departmentAbbreviation", source = "departmentAbbreviation")
    @Mapping(target = "businessOwnership.department", source = "department")
    App targetToSource(AppPO appPO);
}
