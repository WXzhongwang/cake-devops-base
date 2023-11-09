package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.aggregate.AppMember;
import com.rany.cake.devops.base.infra.po.AppMemberPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 应用成员
 *
 * @author zhongshengwang
 * @description 应用成员
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface AppMemberDataConvertor extends BaseConvertor<AppMember, AppMemberPO> {


    /**
     * 聚合根转PO
     *
     * @param appMember 聚合根
     * @return PO
     */
    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "appId.id", target = "appId")
    @Override
    AppMemberPO sourceToTarget(AppMember appMember);

    /**
     * PO转聚合根
     *
     * @param appMemberPO PO
     * @return 聚合根
     */

    @Override
    @Mapping(target = "id.id", source = "id")
    @Mapping(target = "appId.id", source = "appId")
    AppMember targetToSource(AppMemberPO appMemberPO);
}
