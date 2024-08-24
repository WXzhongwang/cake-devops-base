package com.rany.cake.devops.base.api.command.app;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 修改环境变量
 *
 * @author zhongshengwang
 * @description configMap
 * @date 2023/1/28 20:10
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ScaleEnvCommand extends BaseCommand {
    private String envId;
    private Integer replicas;
}
