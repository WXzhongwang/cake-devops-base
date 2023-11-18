package com.rany.cake.devops.base.service.plugins.machine;

import com.rany.cake.devops.base.service.context.DeployContext;

import java.util.List;

/**
 * 打包机器选择
 */
public interface MachineSelectLoadBalance {

    Machine doSelect(List<Machine> machines, DeployContext deployContext);
}
