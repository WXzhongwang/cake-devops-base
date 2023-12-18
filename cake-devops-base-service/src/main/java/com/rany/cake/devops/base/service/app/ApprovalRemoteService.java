package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.approval.*;
import com.rany.cake.devops.base.api.dto.ApprovalDTO;
import com.rany.cake.devops.base.api.query.ApprovalBasicQuery;
import com.rany.cake.devops.base.api.service.ApprovalService;
import com.rany.cake.devops.base.domain.aggregate.Approval;
import com.rany.cake.devops.base.domain.base.SnowflakeIdWorker;
import com.rany.cake.devops.base.domain.enums.ApprovalStatus;
import com.rany.cake.devops.base.domain.pk.ApprovalId;
import com.rany.cake.devops.base.domain.service.ApprovalDomainService;
import com.rany.cake.devops.base.service.adapter.ApprovalDataAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;

@Service
@ShenyuService("/approval/**")
@Slf4j
@AllArgsConstructor
public class ApprovalRemoteService implements ApprovalService {
    private final SnowflakeIdWorker snowflakeIdWorker;
    private final ApprovalDomainService approvalDomainService;
    private final ApprovalDataAdapter approvalDataAdapter;

    @Override
    public PojoResult<String> createApproval(CreateApprovalCommand createApprovalCommand) {
        Approval approval = new Approval(new ApprovalId(String.valueOf(snowflakeIdWorker.nextId())), createApprovalCommand.getDocAddress(),
                createApprovalCommand.getChangeDate(),
                ApprovalStatus.PENDING.name(),
                createApprovalCommand.getComment());
        approvalDomainService.save(approval);
        return PojoResult.succeed(approval.getApprovalId().getApprovalId());
    }

    @Override
    public PojoResult<ApprovalDTO> getApproval(ApprovalBasicQuery approvalBasicQuery) {
        Approval approval = approvalDomainService.getApproval(new ApprovalId(approvalBasicQuery.getApprovalId()));
        return PojoResult.succeed(approvalDataAdapter.sourceToTarget(approval));
    }

    @Override
    public PojoResult<Boolean> deleteApproval(DeleteApprovalCommand deleteApprovalCommand) {
        Approval approval = approvalDomainService.getApproval(new ApprovalId(deleteApprovalCommand.getApprovalId()));
        approval.delete();
        approvalDomainService.update(approval);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> modifyApproval(ModifyApprovalCommand modifyApprovalCommand) {
        Approval approval = approvalDomainService.getApproval(new ApprovalId(modifyApprovalCommand.getApprovalId()));
        approval.modify();
        approval.setDocAddress(modifyApprovalCommand.getDocAddress());
        approval.setChangeDate(modifyApprovalCommand.getChangeDate());
        approval.setComment(modifyApprovalCommand.getComment());
        approvalDomainService.update(approval);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> approveApproval(ApproveApprovalCommand approveApprovalCommand) {
        Approval approval = approvalDomainService.getApproval(new ApprovalId(approveApprovalCommand.getApprovalId()));
        approval.approve();
        approvalDomainService.update(approval);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> repealApproval(RepealApprovalCommand repealApprovalCommand) {
        Approval approval = approvalDomainService.getApproval(new ApprovalId(repealApprovalCommand.getApprovalId()));
        approval.repeal();
        approvalDomainService.update(approval);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> rejectApproval(RejectApprovalCommand rejectApprovalCommand) {
        Approval approval = approvalDomainService.getApproval(new ApprovalId(rejectApprovalCommand.getApprovalId()));
        approval.reject();
        approvalDomainService.update(approval);
        return PojoResult.succeed(Boolean.TRUE);
    }
}
