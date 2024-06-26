package com.rany.cake.devops.base.api.query.approval;

import com.rany.cake.devops.base.api.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询审批单
 *
 * @author zhongshengwang
 * @description 查询审批单
 * @date 2022/12/31 16:57
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApprovalBasicQuery extends BaseQuery {

    private String approvalId;
}
