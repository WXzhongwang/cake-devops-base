package com.rany.cake.devops.base.api.command.host.env;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 同步主机环境变量
 *
 * @author zhongshengwang
 * @description 同步主机环境变量
 * @date 2022/12/27 20:40
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AsyncHostEnvCommand extends BaseCommand {
    
    private Long machineId;


    private List<Long> targetMachineIdList;
}
