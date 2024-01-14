package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.ReleaseDTO;
import com.rany.cake.devops.base.api.query.ReleasePageQuery;
import com.rany.cake.devops.base.domain.aggregate.Release;
import com.rany.cake.devops.base.domain.repository.param.ReleasePageQueryParam;
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
    @Mapping(source = "releaseId.releaseId", target = "releaseId")
    @Mapping(source = "appId.appId", target = "appId")
    @Mapping(source = "approvalId.approvalId", target = "approvalId")
    @Mapping(source = "rollbackId.releaseId", target = "rollbackId")
    ReleaseDTO sourceToTarget(Release release);

    /**
     * PO转聚合根
     *
     * @param releaseDTO PO
     * @return 聚合根
     */

    @Mapping(target = "releaseId.releaseId", source = "releaseId")
    @Mapping(target = "appId.appId", source = "appId")
    @Mapping(target = "approvalId.approvalId", source = "approvalId")
    @Mapping(target = "rollbackId.releaseId", source = "rollbackId")
    Release targetToSource(ReleaseDTO releaseDTO);


    ReleasePageQueryParam convertParam(ReleasePageQuery releasePageQuery);
}
