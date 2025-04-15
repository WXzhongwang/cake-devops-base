package com.rany.cake.devops.base.api.command.system;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统配置请求
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/2/18 22:23
 */
@Data
@ApiModel(value = "系统配置请求")
@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = true)
public class SystemOptionCommand extends BaseCommand {

    @ApiModelProperty(value = "配置项")
    private Integer option;

    @ApiModelProperty(value = "配置值")
    private String value;

}
