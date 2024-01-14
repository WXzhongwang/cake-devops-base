package com.rany.cake.devops.base.service.context;

/**
 * pipeline执行进度跟踪
 */
public interface ProgressObserver {
    void updateProgress(DeployContext deployContext);
}
