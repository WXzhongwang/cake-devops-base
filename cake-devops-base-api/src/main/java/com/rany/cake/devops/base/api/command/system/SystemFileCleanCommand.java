package com.rany.cake.devops.base.api.command.system;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件清理请求
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/2/17 23:43
 */
@Data
@ApiModel(value = "文件清理请求")
@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = true)
public class SystemFileCleanCommand extends BaseCommand {


    @ApiModelProperty(value = "文件清理类型")
    private Integer cleanType;

}
