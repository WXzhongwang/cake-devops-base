package com.rany.cake.devops.base.api.service;

import com.rany.cake.devops.base.api.command.approval.*;
import com.rany.cake.devops.base.api.dto.ApprovalDTO;
import com.rany.cake.devops.base.api.query.approval.ApprovalBasicQuery;

/**
 * 审批服务
 *
 * @author zhongshengwang
 * @description 应用服务
 * @date 2023/1/15 17:33
 * @email 18668485565163.com
 */
public interface ApprovalService {


    /**
     * 创建审批单
     *
     * @param createApprovalCommand 创建审批单
     * @return 成功
     */
    String createApproval(CreateApprovalCommand createApprovalCommand);

    /**
     * 获取审批单信息
     *
     * @param approvalBasicQuery 获取审批单信息
     * @return 审批信息
     */
    ApprovalDTO getApproval(ApprovalBasicQuery approvalBasicQuery);


    /**
     * 删除审批单
     *
     * @param deleteApprovalCommand 删除审批单
     * @return 成功
     */
    Boolean deleteApproval(DeleteApprovalCommand deleteApprovalCommand);

    /**
     * 更新审批单基本信息
     *
     * @param modifyApprovalCommand 更新审批单基本信息
     * @return 成功
     */
    Boolean modifyApproval(ModifyApprovalCommand modifyApprovalCommand);


    /**
     * 通过审批单
     *
     * @param approveApprovalCommand 通过审批单
     * @return 成功
     */
    Boolean approveApproval(ApproveApprovalCommand approveApprovalCommand);

    /**
     * 撤销审批单
     *
     * @param repealApprovalCommand 撤销审批单
     * @return 成功
     */
    Boolean repealApproval(RepealApprovalCommand repealApprovalCommand);

    /**
     * 拒绝审批单
     *
     * @param rejectApprovalCommand 拒绝审批单
     * @return 成功
     */
    Boolean rejectApproval(RejectApprovalCommand rejectApprovalCommand);
}
