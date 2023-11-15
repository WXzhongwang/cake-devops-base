package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.aggregate.Approval;
import com.rany.cake.devops.base.domain.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.domain.pk.ApprovalId;
import com.rany.cake.devops.base.domain.repository.ApprovalRepository;
import com.rany.cake.devops.base.infra.convertor.ApprovalDataConvertor;
import com.rany.cake.devops.base.infra.dao.ApprovalDao;
import com.rany.cake.devops.base.infra.mapper.ApprovalPOMapper;
import com.rany.cake.devops.base.infra.po.ApprovalPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * 审批
 *
 * @author zhongshengwang
 * @description 审批
 * @date 2023/1/28 21:02
 * @email 18668485565163.com
 */
@Service
@AllArgsConstructor
public class ApprovalRepositoryImpl implements ApprovalRepository {

    private final ApprovalPOMapper approvalPOMapper;
    private final ApprovalDao approvalDao;
    private final ApprovalDataConvertor approvalDataConvertor;

    @Override
    public Approval find(@NotNull ApprovalId approvalId) {
        ApprovalPO approvalPO = approvalPOMapper.selectByPrimaryKey(approvalId.getId());
        return approvalDataConvertor.targetToSource(approvalPO);
    }

    @Override
    public void remove(@NotNull Approval approval) {
        ApprovalPO approvalPO = approvalDataConvertor.sourceToTarget(approval);
        approvalPO.setIsDeleted(DeleteStatusEnum.YES.getValue());
        approvalPOMapper.updateByPrimaryKey(approvalPO);
    }

    @Override
    public void save(@NotNull Approval approval) {
        approvalDao.save(approval);
    }

    @Override
    public int update(Approval approval) {
        return approvalDao.update(approval);
    }
}
