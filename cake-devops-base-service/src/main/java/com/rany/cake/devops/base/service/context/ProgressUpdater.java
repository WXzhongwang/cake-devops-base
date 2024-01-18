package com.rany.cake.devops.base.service.context;

import com.rany.cake.devops.base.domain.aggregate.Release;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.enums.ReleaseStatus;
import com.rany.cake.devops.base.domain.repository.AppRepository;
import com.rany.cake.devops.base.domain.repository.ReleaseRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;


public class ProgressUpdater implements ProgressObserver {

    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private ReleaseRepository releaseRepository;
    @Resource
    private AppRepository appRepository;

    @Override
    public void updateProgress(DeployContext deployContext) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(deployContext.getProgress().getPipeKey(), deployContext.dump());
        Release release = deployContext.getRelease();
        AppEnv appEnv = deployContext.getAppEnv();
        if (StringUtils.equals(release.getReleaseStatus(), ReleaseStatus.FAILED.name()) ||
                StringUtils.equals(release.getReleaseStatus(), ReleaseStatus.FINISHED.name())) {
            appEnv.recover();
        }
        if (StringUtils.equals(release.getReleaseStatus(), ReleaseStatus.PENDING.name())) {
            appEnv.deploy();
        }
        appRepository.updateAppEnv(appEnv);
        releaseRepository.update(release);
    }
}
