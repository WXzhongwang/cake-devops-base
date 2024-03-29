package com.rany.cake.devops.base.api.command.release;


import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 添加发布
 *
 * @author zhongshengwang
 * @description 添加发布
 * @date 2022/12/27 20:40
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateReleaseCommand extends BaseCommand {

    /**
     * 应用ID
     */
    private String appId;
    /**
     * 环境ID
     */
    private String envId;
    /**
     * 关联审批ID
     */
    private String approvalId;
    /**
     * 预计发布日期
     */
    private Date releaseDate;
    /**
     * 分支
     */
    private String releaseBranch;
    /**
     * commitId
     */
    private String releaseCommitId;
    /**
     * 正式环境发布需填写版本号
     */
    private String releaseVersion;

    /**
     * 回滚发布标识
     */
    private String rollback;
    /**
     * 回滚到哪一次发布
     */
    private String rollbackId;


    /**
     * 文档地址
     */
    private String docAddress;
    /**
     * 备注
     */
    private String comment;
}
