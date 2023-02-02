package com.rany.cake.devops.base.domain.convertor;

import com.rany.cake.devops.base.domain.aggregate.AppMember;
import com.rany.cake.devops.base.po.AppMemberPO;
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
public interface AppMemberDataConvertor extends BaseConvertor<AppMember, AppMemberPO> {


    /**
     * 聚合根转PO
     *
     * @param appMember
     * @return
     */
    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "appId.id", target = "appId")
    @Override
    AppMemberPO sourceToTarget(AppMember appMember);

    /**
     * PO转聚合根
     *
     * @param appMemberPO
     * @return
     */

    @Override
    @Mapping(target = "id.id", source = "id")
    @Mapping(target = "appId.id", source = "appId")
    AppMember targetToSource(AppMemberPO appMemberPO);
}
