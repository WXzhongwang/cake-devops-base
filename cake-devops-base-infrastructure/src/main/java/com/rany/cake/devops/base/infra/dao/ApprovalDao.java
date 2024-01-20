package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.aggregate.Approval;
import com.rany.cake.devops.base.infra.po.ApprovalPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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


    ApprovalPO selectByApprovalId(@Param("approvalId") String approvalId);

    List<ApprovalPO> selectByApprovalIds(@Param("approvalIds") List<String> approvalIds);


    /**
     * 更新
     *
     * @param approval 审批
     * @return
     */
    int update(Approval approval);

}
