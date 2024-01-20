package com.rany.cake.devops.base.service.context;

/**
 * pipeline执行进度跟踪
 */
public interface ProgressObserver {
    /**
     * 进度更新
     *
     * @param deployContext 上下文
     */
    void updateProgress(DeployContext deployContext);
}
