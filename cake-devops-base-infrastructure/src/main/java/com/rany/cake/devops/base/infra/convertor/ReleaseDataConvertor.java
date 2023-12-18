package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.aggregate.Release;
import com.rany.cake.devops.base.infra.po.ReleasePO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 发布转换器
 *
 * @author zhongshengwang
 * @description 发布转换器
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
    @Mapping(source = "releaseId.releaseId", target = "releaseId")
    @Mapping(source = "appId.appId", target = "appId")
    @Mapping(source = "approvalId.approvalId", target = "approvalId")
    @Mapping(source = "rollbackId.releaseId", target = "rollbackId")
    @Override
    ReleasePO sourceToTarget(Release release);

    /**
     * PO转聚合根
     *
     * @param releasePO PO
     * @return 聚合根
     */

    @Override
    @Mapping(target = "releaseId.releaseId", source = "releaseId")
    @Mapping(target = "appId.appId", source = "appId")
    @Mapping(target = "approvalId.approvalId", source = "approvalId")
    @Mapping(target = "rollbackId.releaseId", source = "rollbackId")
    Release targetToSource(ReleasePO releasePO);
}
