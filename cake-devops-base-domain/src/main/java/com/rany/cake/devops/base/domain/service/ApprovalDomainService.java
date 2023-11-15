package com.rany.cake.devops.base.domain.service;

import com.rany.cake.devops.base.domain.aggregate.Approval;
import com.rany.cake.devops.base.domain.pk.ApprovalId;
import com.rany.cake.devops.base.domain.repository.ApprovalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 审批
 *
 * @author zhongshengwang
 * @description 审批
 * @date 2023/1/28 20:59
 * @email 18668485565163.com
 */
@Component
@AllArgsConstructor
public class ApprovalDomainService {

    private final ApprovalRepository approvalRepository;

    /**
     * 创建审批单
     *
     * @param approval
     */

    public void save(Approval approval) {
        approvalRepository.save(approval);
    }

    public void update(Approval approval) {
        approvalRepository.update(approval);
    }

    public Approval getApproval(ApprovalId approvalId) {
        return approvalRepository.find(approvalId);
    }
}
