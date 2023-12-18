package com.rany.cake.devops.base.api.command.approval;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 更新审批单
 *
 * @author zhongshengwang
 * @description 更新审批单
 * @date 2022/12/30 22:02
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ModifyApprovalCommand extends BaseCommand {

    private String approvalId;

    private String docAddress;
    private Date changeDate;
    private String comment;
}
