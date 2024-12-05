package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.approval.*;
import com.rany.cake.devops.base.api.dto.ApprovalDTO;
import com.rany.cake.devops.base.api.query.approval.ApprovalBasicQuery;
import com.rany.cake.devops.base.api.service.ApprovalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 审批单
 *
 * @author zhongshengwang
 */
@RestController
@RequestMapping("/api/devops/approval")
public class ApprovalController {

    @Resource
    private ApprovalService approvalService;


    @PostMapping("/create")
    public PojoResult<String> createApproval(CreateApprovalCommand createApprovalCommand) {
        return PojoResult.succeed(approvalService.createApproval(createApprovalCommand));
    }

    @PostMapping("/get")
    public PojoResult<ApprovalDTO> getApproval(ApprovalBasicQuery approvalBasicQuery) {
        return PojoResult.succeed(approvalService.getApproval(approvalBasicQuery));
    }

    @PostMapping("/delete")
    public PojoResult<Boolean> deleteApproval(DeleteApprovalCommand deleteApprovalCommand) {
        return PojoResult.succeed(approvalService.deleteApproval(deleteApprovalCommand));
    }

    @PostMapping("/modify")
    public PojoResult<Boolean> modifyApproval(ModifyApprovalCommand modifyApprovalCommand) {
        return PojoResult.succeed(approvalService.modifyApproval(modifyApprovalCommand));
    }

    @PostMapping("/approve")
    public PojoResult<Boolean> approveApproval(ApproveApprovalCommand approveApprovalCommand) {
        return PojoResult.succeed(approvalService.approveApproval(approveApprovalCommand));
    }

    @PostMapping("/reject")
    public PojoResult<Boolean> rejectApproval(RejectApprovalCommand rejectApprovalCommand) {
        return PojoResult.succeed(approvalService.rejectApproval(rejectApprovalCommand));
    }

    @PostMapping("/repeal")
    public PojoResult<Boolean> repealApproval(RepealApprovalCommand repealApprovalCommand) {
        return PojoResult.succeed(approvalService.repealApproval(repealApprovalCommand));
    }
}
