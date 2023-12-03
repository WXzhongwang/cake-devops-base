package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.approval.*;
import com.rany.cake.devops.base.api.dto.ApprovalDTO;
import com.rany.cake.devops.base.api.query.ApprovalBasicQuery;
import com.rany.cake.devops.base.api.service.ApprovalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/approval")
public class ApprovalController {

    @Resource
    private ApprovalService approvalService;


    @PostMapping("/create")
    public PojoResult<Long> createApproval(CreateApprovalCommand createApprovalCommand) {
        return approvalService.createApproval(createApprovalCommand);
    }

    @PostMapping("/get")
    public PojoResult<ApprovalDTO> createApproval(ApprovalBasicQuery approvalBasicQuery) {
        return approvalService.getApproval(approvalBasicQuery);
    }

    @PostMapping("/delete")
    public PojoResult<Boolean> deleteApproval(DeleteApprovalCommand deleteApprovalCommand) {
        return approvalService.deleteApproval(deleteApprovalCommand);
    }

    @PostMapping("/modify")
    public PojoResult<Boolean> modifyApproval(ModifyApprovalCommand modifyApprovalCommand) {
        return approvalService.modifyApproval(modifyApprovalCommand);
    }

    @PostMapping("/approve")
    public PojoResult<Boolean> approveApproval(ApproveApprovalCommand approveApprovalCommand) {
        return approvalService.approveApproval(approveApprovalCommand);
    }

    @PostMapping("/reject")
    public PojoResult<Boolean> rejectApproval(RejectApprovalCommand rejectApprovalCommand) {
        return approvalService.rejectApproval(rejectApprovalCommand);
    }

    @PostMapping("/repeal")
    public PojoResult<Boolean> repealApproval(RepealApprovalCommand repealApprovalCommand) {
        return approvalService.repealApproval(repealApprovalCommand);
    }
}
