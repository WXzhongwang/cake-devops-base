package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.ReleaseDTO;
import com.rany.cake.devops.base.domain.aggregate.Release;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 发布
 *
 * @author zhongshengwang
 * @description 发布
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface ReleaseDataAdapter extends BaseConvertor<Release, ReleaseDTO> {


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
    ReleaseDTO sourceToTarget(Release release);

    /**
     * PO转聚合根
     *
     * @param releaseDTO PO
     * @return 聚合根
     */

    @Mapping(target = "id.id", source = "id")
    @Mapping(target = "appId.id", source = "appId")
    @Mapping(target = "approvalId.id", source = "approvalId")
    @Mapping(target = "rollbackId.id", source = "rollbackId")
    Release targetToSource(ReleaseDTO releaseDTO);
}
