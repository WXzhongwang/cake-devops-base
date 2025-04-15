package com.rany.cake.devops.base.domain.aggregate;

import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.cake.devops.base.domain.pk.ApprovalId;
import com.rany.cake.devops.base.util.enums.ApprovalStatus;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import com.rany.cake.toolkit.lang.time.Dates;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;

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
@EqualsAndHashCode(callSuper = false)
public class Approval extends BaseAggregateRoot implements IAggregate<ApprovalId> {

    private ApprovalId approvalId;
    private String docAddress;
    private Date changeDate;
    private String approvalStatus;
    private String comment;


    public Approval(ApprovalId approvalId, String docAddress, Date changeDate, String approvalStatus, String comment) {
        this.approvalId = approvalId;
        this.docAddress = docAddress;
        this.changeDate = changeDate;
        this.approvalStatus = approvalStatus;
        this.comment = comment;

    }

    public void init(Boolean needApproval) {
        this.isDeleted = DeleteStatusEnum.NO.getValue();
        this.gmtCreate = new Date();
        this.gmtModified = this.gmtCreate;
        if (BooleanUtils.isFalse(needApproval)) {
            this.approvalStatus = ApprovalStatus.AUTO_APPROVED.name();
        }
    }

    public Boolean delete() {
        this.gmtModified = Dates.date();
        this.isDeleted = DeleteStatusEnum.YES.getValue();
        return Boolean.TRUE;
    }

    public Boolean modify() {
        this.gmtModified = Dates.date();
        return Boolean.TRUE;
    }

    public void approve() {
        this.gmtModified = Dates.date();
        this.approvalStatus = ApprovalStatus.APPROVED.name();
    }

    public void reject() {
        this.gmtModified = Dates.date();
        this.approvalStatus = ApprovalStatus.REJECTED.name();
    }

    public void repeal() {
        this.gmtModified = Dates.date();
        this.approvalStatus = ApprovalStatus.REPEALED.name();
    }

    @Override
    public ApprovalId getBizID() {
        return approvalId;
    }
}
