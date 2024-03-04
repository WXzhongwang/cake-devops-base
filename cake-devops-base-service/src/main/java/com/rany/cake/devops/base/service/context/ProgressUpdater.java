package com.rany.cake.devops.base.service.context;

import com.rany.cake.devops.base.domain.aggregate.Release;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.repository.AppRepository;
import com.rany.cake.devops.base.domain.repository.ReleaseRepository;
import com.rany.cake.devops.base.util.enums.ReleaseStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class ProgressUpdater implements ProgressObserver {
    @Resource
    private ReleaseRepository releaseRepository;
    @Resource
    private AppRepository appRepository;

    @Override
    public void updateProgress(DeployContext deployContext) {
        Release release = deployContext.getRelease();
        AppEnv appEnv = deployContext.getAppEnv();
        // 设定当前应用环境下最新进度
        if (StringUtils.equals(release.getReleaseStatus(), ReleaseStatus.FAILED.name()) ||
                StringUtils.equals(release.getReleaseStatus(), ReleaseStatus.FINISHED.name())) {
            appEnv.recover(deployContext.dump());
        }
        if (StringUtils.equals(release.getReleaseStatus(), ReleaseStatus.PENDING.name())) {
            appEnv.deploy(deployContext.dump());
        }
        // 进度更新
        appRepository.updateAppEnv(appEnv);
        // release状态更新
        releaseRepository.update(release);
    }
}
