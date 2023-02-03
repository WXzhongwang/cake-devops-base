package com.rany.cake.devops.base.domain.convertor;

import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.infra.po.AppPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface AppDataConvertor extends BaseConvertor<App, AppPO> {


    /**
     * 聚合根转PO
     *
     * @param app
     * @return
     */
    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "appName.name", target = "appName")
    @Mapping(source = "codeRepository.repo", target = "repo")
    @Mapping(source = "codeRepository.defaultBranch", target = "defaultBranch")
    @Mapping(source = "businessOwnership.businessUnit", target = "businessUnit")
    @Mapping(source = "businessOwnership.bu", target = "bu")
    @Mapping(source = "businessOwnership.department", target = "department")
    @Override
    AppPO sourceToTarget(App app);

    /**
     * PO转聚合根
     *
     * @param appPO
     * @return
     */

    @Override
    @Mapping(target = "id.id", source = "id")
    @Mapping(target = "appName.name", source = "appName")
    @Mapping(target = "codeRepository.repo", source = "repo")
    @Mapping(target = "codeRepository.defaultBranch", source = "defaultBranch")
    @Mapping(target = "businessOwnership.businessUnit", source = "businessUnit")
    @Mapping(target = "businessOwnership.bu", source = "bu")
    @Mapping(target = "businessOwnership.department", source = "department")
    App targetToSource(AppPO appPO);
}
