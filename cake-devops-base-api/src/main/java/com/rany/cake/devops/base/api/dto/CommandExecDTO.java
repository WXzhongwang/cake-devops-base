package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author zhongshengwang
 * @version 1.0
 * @date 2025/4/14 16:45
 * @slogon 找到银弹
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommandExecDTO extends DTO {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "执行用户id")
    private String accountId;

    @ApiModelProperty(value = "执行用户名称")
    private String username;

    @ApiModelProperty(value = "类型")
    private Integer execType;

    @ApiModelProperty(value = "状态")
    private Integer execStatus;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "执行机器id")
    private String hostId;

    @ApiModelProperty(value = "执行机器名称")
    private String hostName;

    @ApiModelProperty(value = "执行机器主机")
    private String host;

    @ApiModelProperty(value = "执行退出码")
    private Integer exitCode;

    @ApiModelProperty(value = "执行命令")
    private String execCommand;

    @ApiModelProperty(value = "执行开始时间")
    private Date startDate;

    @ApiModelProperty(value = "执行开始时间")
    private String startDateAgo;

    @ApiModelProperty(value = "执行结束时间")
    private Date endDate;

    @ApiModelProperty(value = "执行结束时间")
    private String endDateAgo;

    @ApiModelProperty(value = "使用时间毫秒")
    private Long used;

    @ApiModelProperty(value = "使用时间")
    private String keepTime;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "创建时间")
    private String createTimeAgo;
}
