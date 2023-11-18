package com.rany.cake.devops.base.service.plugins.machine;

import com.rany.cake.devops.base.service.context.DeployContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component(value = "weightRandom")
public class RandomWeightLoadBalancer implements MachineSelectLoadBalance {

    private final Random random = new Random();

    @Override
    public Machine doSelect(List<Machine> machines, DeployContext deployContext) {
        if (machines == null || machines.isEmpty()) {
            return null;
        }

        // 计算总权重
        int totalWeight = machines.stream().mapToInt(Machine::getWeight).sum();
        // 生成一个介于 0 到总权重之间的随机数
        int randomWeight = random.nextInt(totalWeight);
        // 遍历服务器列表，选择权重大于随机数的第一台服务器
        int currentWeight = 0;
        for (Machine machine : machines) {
            currentWeight += machine.getWeight();
            if (randomWeight < currentWeight) {
                return machine;
            }
        }
        // 如果未能选择服务器，返回列表中的第一台服务器
        return machines.get(0);
    }
}
