package com.rany.cake.devops.base.api.command.approval;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 通过审批单
 *
 * @author zhongshengwang
 * @description 删除审批单
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApproveApprovalCommand extends BaseCommand {

    private String approvalId;
}
