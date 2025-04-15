package com.rany.cake.devops.base.api.command.system;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhongshengwang
 * @version 1.0
 * @date 2025/4/15 18:27
 * @slogon 找到银弹
 */
@Data
@ApiModel(value = "配置ip过滤器请求")
@EqualsAndHashCode(callSuper = false)
public class ConfigIpListCommand extends BaseCommand {

    @ApiModelProperty(value = "ip白名单")
    private String whiteIpList;

    @ApiModelProperty(value = "ip黑名单")
    private String blackIpList;

    @ApiModelProperty(value = "是否启用ip过滤器")
    private Boolean enableIpFilter;

    @ApiModelProperty(value = "是否启用ip白名单")
    private Boolean enableWhiteIpList;

}