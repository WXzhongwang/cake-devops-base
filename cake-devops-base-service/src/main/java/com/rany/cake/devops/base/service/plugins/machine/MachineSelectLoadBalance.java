package com.rany.cake.devops.base.service.plugins.machine;

import com.rany.cake.devops.base.service.context.DeployContext;

import java.util.List;

public interface MachineSelectLoadBalance {

    Machine doSelect(List<Machine> machines, DeployContext deployContext);
}
