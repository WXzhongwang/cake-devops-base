package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.approval.*;
import com.rany.cake.devops.base.api.dto.ApprovalDTO;
import com.rany.cake.devops.base.api.query.ApprovalBasicQuery;

public interface ApprovalService {


    /**
     * 创建审批单
     *
     * @param createApprovalCommand
     * @return
     */
    PojoResult<Long> createApproval(CreateApprovalCommand createApprovalCommand);

    /**
     * 获取审批单信息
     *
     * @param approvalBasicQuery
     * @return
     */
    PojoResult<ApprovalDTO> getApproval(ApprovalBasicQuery approvalBasicQuery);


    /**
     * 删除审批单
     *
     * @param deleteApprovalCommand
     * @return
     */
    PojoResult<Boolean> deleteApproval(DeleteApprovalCommand deleteApprovalCommand);

    /**
     * 更新审批单基本信息
     *
     * @param modifyApprovalCommand
     * @return
     */
    PojoResult<Boolean> modifyApproval(ModifyApprovalCommand modifyApprovalCommand);


    /**
     * 通过审批单
     *
     * @param approveApprovalCommand
     * @return
     */
    PojoResult<Boolean> approveApproval(ApproveApprovalCommand approveApprovalCommand);

    /**
     * 撤销审批单
     *
     * @param repealApprovalCommand
     * @return
     */
    PojoResult<Boolean> repealApproval(RepealApprovalCommand repealApprovalCommand);

    /**
     * 拒绝审批单
     *
     * @param rejectApprovalCommand
     * @return
     */
    PojoResult<Boolean> rejectApproval(RejectApprovalCommand rejectApprovalCommand);
}
