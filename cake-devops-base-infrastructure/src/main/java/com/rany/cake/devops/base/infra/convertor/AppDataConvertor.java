package com.rany.cake.devops.base.infra.convertor;

import com.alibaba.fastjson.JSON;
import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.valueobject.AppExtend;
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
    @Mapping(source = "appId.appId", target = "appId")
    @Mapping(source = "appName.name", target = "appName")
    @Mapping(source = "codeRepository.repo", target = "repo")
    @Mapping(source = "codeRepository.defaultBranch", target = "defaultBranch")
    @Mapping(source = "codeRepository.codePlatform", target = "codePlatform")
    @Mapping(source = "businessOwnership.departmentAbbreviation", target = "departmentAbbreviation")
    @Mapping(source = "businessOwnership.department", target = "department")
    @Mapping(target = "extend", expression = "java(this.convertExtend(app.getAppExtend()))")
    @Override
    AppPO sourceToTarget(App app);

    /**
     * PO转聚合根
     *
     * @param appPO PO
     * @return 聚合根
     */

    @Override
    @Mapping(target = "appId.appId", source = "appId")
    @Mapping(target = "appName.name", source = "appName")
    @Mapping(target = "codeRepository.repo", source = "repo")
    @Mapping(target = "codeRepository.defaultBranch", source = "defaultBranch")
    @Mapping(target = "codeRepository.codePlatform", source = "codePlatform")
    @Mapping(target = "businessOwnership.departmentAbbreviation", source = "departmentAbbreviation")
    @Mapping(target = "businessOwnership.department", source = "department")
    @Mapping(target = "appExtend", expression = "java(this.reverseExtend(appPO.getExtend()))")
    App targetToSource(AppPO appPO);


    default String convertExtend(AppExtend appExtend) {
        if (appExtend == null) {
            return null;
        }
        return JSON.toJSONString(appExtend);
    }

    default AppExtend reverseExtend(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return JSON.parseObject(value, AppExtend.class);
    }
}
