package com.rany.cake.devops.base.api.command.approval;


import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 添加审批单
 *
 * @author zhongshengwang
 * @description 添加审批单
 * @date 2022/12/27 20:40
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateApprovalCommand extends BaseCommand {

    private String docAddress;
    private Date changeDate;
    private String comment;
}
