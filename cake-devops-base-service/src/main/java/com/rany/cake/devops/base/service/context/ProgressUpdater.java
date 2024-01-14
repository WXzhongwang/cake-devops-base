package com.rany.cake.devops.base.service.context;

public class ProgressUpdater implements ProgressObserver {
    @Override
    public void updateProgress(DeployContext deployContext) {
        // 在这里执行更新进度的操作
        // 可以调用 Redis，更新 Pipeline 执行进度
        // 使用 deployContext 获取必要的信息
        // ...
    }
}
