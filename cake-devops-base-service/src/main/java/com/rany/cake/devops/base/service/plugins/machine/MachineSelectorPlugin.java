package com.rany.cake.devops.base.service.plugins.machine;

import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.service.HostDomainService;
import com.rany.cake.devops.base.service.adapter.HostDataAdapter;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.RunningConstant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 打包机器选择
 *
 * @author zhongshengwang
 * @description 打包机器选择
 * @date 2023/1/20 20:30
 * @email 18668485565163.com
 */
@Component
public class MachineSelectorPlugin extends BasePlugin {
    @Resource
    private HostDomainService hostDomainService;
    @Resource
    private HostDataAdapter hostDataAdapter;
    @Resource
    @Lazy
    @Qualifier("${devops.package.balancer:random}")
    private MachineSelectLoadBalance machineSelector;

    @Override
    public boolean init(DeployContext context) {
        return true;
    }

    @Override
    public boolean execute(DeployContext context) {
        List<Host> packageMachineList = hostDomainService.getPackageMachineList();
        List<Machine> machines = hostDataAdapter.sourceToMachine(packageMachineList);
        Machine machine = machineSelector.doSelect(machines, context);
        this.putArg(RunningConstant.BUILDER_IP, machine.getHostName());
        this.putArg(RunningConstant.BUILDER_PORT, machine.getPort());
        this.putArg(RunningConstant.BUILDER_REMOTE_USER, machine.getUsername());
        this.putArg(RunningConstant.BUILDER_REMOTE_PWD, machine.getPkey());
        return true;
    }
}
