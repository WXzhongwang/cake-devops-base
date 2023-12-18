package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.aggregate.HostGroup;
import com.rany.cake.devops.base.infra.po.HostGroupPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 主机组
 *
 * @author zhongshengwang
 * @description 主机组
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface HostGroupDataConvertor extends BaseConvertor<HostGroup, HostGroupPO> {


    /**
     * 聚合根转PO
     *
     * @param hostGroup 聚合根
     * @return PO
     */
    @Mapping(source = "hostGroupId.hostGroupId", target = "hostGroupId")
    @Override
    HostGroupPO sourceToTarget(HostGroup hostGroup);

    /**
     * PO转聚合根
     *
     * @param hostGroupPO PO
     * @return 聚合根
     */

    @Override
    @Mapping(target = "hostGroupId.hostGroupId", source = "hostGroupId")
    HostGroup targetToSource(HostGroupPO hostGroupPO);
}
