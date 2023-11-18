package com.rany.cake.devops.base.service.plugins.machine;

import com.rany.cake.devops.base.service.context.DeployContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component(value = "random")
public class RandomLoadBalancer implements MachineSelectLoadBalance {

    private final Random random = new Random();

    @Override
    public Machine doSelect(List<Machine> machines, DeployContext deployContext) {
        if (machines == null || machines.isEmpty()) {
            return null;
        }
        // 获取机器列表的大小
        int machineCount = machines.size();
        // 通过随机数选择一个索引
        int selectedIndex = random.nextInt(machineCount);
        // 返回选择的机器
        return machines.get(selectedIndex);
    }
}
