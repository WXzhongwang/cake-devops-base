package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.aggregate.Approval;

/**
 * 审批
 *
 * @author zhongshengwang
 * @description 审批
 * @date 2023/2/2 21:24
 * @email 18668485565163.com
 */
public interface ApprovalDao {


    /**
     * 新增
     *
     * @param approval 审批
     * @return
     */
    int save(Approval approval);


    /**
     * 更新
     *
     * @param approval 审批
     * @return
     */
    int update(Approval approval);

}
