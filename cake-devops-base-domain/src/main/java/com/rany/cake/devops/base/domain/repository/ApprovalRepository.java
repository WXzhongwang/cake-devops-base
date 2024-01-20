package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.ddd.repository.Repository;
import com.rany.cake.devops.base.domain.aggregate.Approval;
import com.rany.cake.devops.base.domain.pk.ApprovalId;

import java.util.List;

/**
 * 审批表仓储
 *
 * @author zhongshengwang
 * @description 审批表仓储
 * @date 2023/1/28 21:01
 * @email 18668485565163.com
 */
public interface ApprovalRepository extends Repository<Approval, ApprovalId> {

    int update(Approval approval);

    List<Approval> findByIds(List<String> approvalIds);
}
