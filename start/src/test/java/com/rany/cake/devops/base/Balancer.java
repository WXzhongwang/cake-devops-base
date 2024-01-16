package com.rany.cake.devops.base;

import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.machine.Machine;
import com.rany.cake.devops.base.service.plugins.machine.RandomLoadBalancer;
import com.rany.cake.devops.base.service.plugins.machine.RandomWeightLoadBalancer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Balancer extends BaseTests {
    @Test
    public void test01() {
        // 示例用法
        RandomLoadBalancer loadBalancer = new RandomLoadBalancer();
        // 假设有三台机器
        List<Machine> machines = new ArrayList<>();
        // 进行随机轮询选择
        Machine selectedMachine = loadBalancer.doSelect(machines, new DeployContext(null));
        // 打印选择的机器信息
        System.out.println("Selected Machine: " + selectedMachine);
    }

    @Test
    public void test02() {
        // 示例用法
        RandomWeightLoadBalancer loadBalancer = new RandomWeightLoadBalancer();
        // 假设有三台机器，分别设置不同的权重
        // 假设有三台机器
        List<Machine> machines = new ArrayList<>();
        // 进行加权轮询选择
        Machine selectedMachine = loadBalancer.doSelect(machines, new DeployContext(null));
        // 打印选择的机器信息
        System.out.println("Selected Machine: " + selectedMachine);
    }
}
