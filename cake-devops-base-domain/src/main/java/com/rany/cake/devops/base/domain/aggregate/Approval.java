package com.rany.cake.devops.base.domain.aggregate;

import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.cake.devops.base.domain.pk.ApprovalId;
import lombok.*;

import java.util.Date;

/**
 * 发布审批单表
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 15:53
 * @email 18668485565163.com
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Approval extends BaseAggregateRoot implements IAggregate<ApprovalId> {

    private ApprovalId id;
    private String docAddress;
    private Date changeDate;
    private String approvalStatus;
    private String comment;
}
