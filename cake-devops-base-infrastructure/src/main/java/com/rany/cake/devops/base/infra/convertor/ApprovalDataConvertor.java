package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.aggregate.Approval;
import com.rany.cake.devops.base.infra.po.ApprovalPO;
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
public interface ApprovalDataConvertor extends BaseConvertor<Approval, ApprovalPO> {


    /**
     * 聚合根转PO
     *
     * @param approval 聚合根
     * @return PO
     */
    @Mapping(source = "approvalId.approvalId", target = "approvalId")
    @Override
    ApprovalPO sourceToTarget(Approval approval);

    /**
     * PO转聚合根
     *
     * @param approvalPO PO
     * @return 聚合根
     */

    @Override
    @Mapping(target = "approvalId.approvalId", source = "approvalId")
    Approval targetToSource(ApprovalPO approvalPO);
}
