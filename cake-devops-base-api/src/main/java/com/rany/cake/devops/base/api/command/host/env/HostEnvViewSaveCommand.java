package com.rany.cake.devops.base.api.command.host.env;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 主机环境变量视图形式保存
 *
 * @author zhongshengwang
 * @description 主机环境变量视图形式保存
 * @date 2022/12/27 20:40
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HostEnvViewSaveCommand extends BaseCommand {

    private String config;
    
    /**
     * 视图类型
     */
    private Integer viewType;

}
