package com.rany.cake.devops.base.service.plugins.machine;

import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.service.adapter.HostDataAdapter;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.annotation.PluginName;
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
@PluginName("工作机选择")
public class MachineSelectorPlugin extends BasePlugin {
    @Resource
    private HostDataAdapter hostDataAdapter;
    @Resource
    @Lazy
    @Qualifier("random")
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
//        context.putArg(RunningConstant.BUILDER_IP, machine.getServerAddr());
//        context.putArg(RunningConstant.BUILDER_PORT, machine.getPort());
//        context.putArg(RunningConstant.BUILDER_REMOTE_USER, machine.getUsername());
//        context.putArg(RunningConstant.BUILDER_REMOTE_PWD, machine.get());
        context.setHostId(machine.getHostId());
        return true;
    }
}
