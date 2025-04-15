package com.rany.cake.devops.base.api.dto.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 系统配置响应
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/2/18 22:53
 */
@Data
@ApiModel(value = "系统配置响应")
@SuppressWarnings("ALL")
public class SystemOptionDTO {


    @ApiModelProperty(value = "是否启用自动清理")
    private Boolean autoCleanFile;


    @ApiModelProperty(value = "文件清理阈值")
    private Integer fileCleanThreshold;

    @ApiModelProperty(value = "允许多端登陆")
    private Boolean allowMultipleLogin;


    @ApiModelProperty(value = "是否启用登陆失败锁定")
    private Boolean loginFailureLock;


    @ApiModelProperty(value = "是否启用登陆IP绑定")
    private Boolean loginIpBind;

    @ApiModelProperty(value = "是否启用登陆IP绑定")
    private Boolean loginTokenAutoRenew;


    @ApiModelProperty(value = "登陆凭证有效期")
    private Integer loginTokenExpire;


    @ApiModelProperty(value = "登陆失败锁定阈值")
    private Integer loginFailureLockThreshold;


    @ApiModelProperty(value = "登陆自动续签阈值")
    private Integer loginTokenAutoRenewThreshold;


    @ApiModelProperty(value = "自动恢复启用的调度任务")
    private Boolean resumeEnableSchedulerTask;


    @ApiModelProperty(value = "终端后台主动推送心跳")
    private Boolean terminalActivePushHeartbeat;


    @ApiModelProperty(value = "SFTP 上传文件最大阈值 (MB)")
    private Integer sftpUploadThreshold;

    @ApiModelProperty(value = "统计缓存有效时间 (分)")
    private Integer statisticsCacheExpire;

}
