package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.aggregate.Release;
import com.rany.cake.devops.base.infra.po.ReleasePO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 审批转换器
 *
 * @author zhongshengwang
 * @description 审批转换器
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface ReleaseDataConvertor extends BaseConvertor<Release, ReleasePO> {


    /**
     * 聚合根转PO
     *
     * @param release 聚合根
     * @return PO
     */
    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "appId.id", target = "appId")
    @Mapping(source = "approvalId.id", target = "approvalId")
    @Mapping(source = "rollbackId.id", target = "rollbackId")
    @Override
    ReleasePO sourceToTarget(Release release);

    /**
     * PO转聚合根
     *
     * @param releasePO PO
     * @return 聚合根
     */

    @Override
    @Mapping(target = "id.id", source = "id")
    @Mapping(target = "appId.id", source = "appId")
    @Mapping(target = "approvalId.id", source = "approvalId")
    @Mapping(target = "rollbackId.id", source = "rollbackId")
    Release targetToSource(ReleasePO releasePO);
}
