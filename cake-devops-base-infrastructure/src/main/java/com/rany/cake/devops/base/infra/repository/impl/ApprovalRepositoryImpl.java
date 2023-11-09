package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.aggregate.Approval;
import com.rany.cake.devops.base.domain.pk.ApprovalId;
import com.rany.cake.devops.base.domain.repository.ApprovalRepository;
import com.rany.cake.devops.base.infra.convertor.ApprovalDataConvertor;
import com.rany.cake.devops.base.infra.mapper.ApprovalPOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/28 21:02
 * @email 18668485565163.com
 */
@Service
@AllArgsConstructor
public class ApprovalRepositoryImpl implements ApprovalRepository {

    private final ApprovalPOMapper approvalPOMapper;
    private final ApprovalDataConvertor approvalDataConvertor;

    @Override
    public Approval find(@NotNull ApprovalId approvalId) {
        return null;
    }

    @Override
    public void remove(@NotNull Approval approval) {

    }

    @Override
    public void save(@NotNull Approval approval) {

    }
}
