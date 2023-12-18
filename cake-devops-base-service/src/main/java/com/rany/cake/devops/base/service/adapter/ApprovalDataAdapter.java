package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.ApprovalDTO;
import com.rany.cake.devops.base.domain.aggregate.Approval;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
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
public interface ApprovalDataAdapter extends BaseConvertor<Approval, ApprovalDTO> {


    /**
     * 聚合根转PO
     *
     * @param approval 聚合根
     * @return PO
     */
    @Mapping(source = "approvalId.approvalId", target = "approvalId")
    ApprovalDTO sourceToTarget(Approval approval);

    /**
     * PO转聚合根
     *
     * @param approvalDTO PO
     * @return 聚合根
     */

    @Mapping(target = "approvalId.approvalId", source = "approvalId")
    Approval targetToSource(ApprovalDTO approvalDTO);
}
