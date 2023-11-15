package com.rany.cake.devops.base.domain.aggregate;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.cake.devops.base.domain.enums.ApprovalStatus;
import com.rany.cake.devops.base.domain.enums.DeleteStatusEnum;
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

    public Boolean delete() {
        this.gmtModified = DateUtil.date();
        this.isDeleted = DeleteStatusEnum.YES.getValue();
        return Boolean.TRUE;
    }

    public Boolean modify() {
        this.gmtModified = DateUtil.date();
        return Boolean.TRUE;
    }

    public void approve() {
        this.gmtModified = DateUtil.date();
        this.approvalStatus = ApprovalStatus.APPROVED.name();
    }

    public void reject() {
        this.gmtModified = DateUtil.date();
        this.approvalStatus = ApprovalStatus.REJECTED.name();
    }

    public void repeal() {
        this.gmtModified = DateUtil.date();
        this.approvalStatus = ApprovalStatus.REPEALED.name();
    }
}
