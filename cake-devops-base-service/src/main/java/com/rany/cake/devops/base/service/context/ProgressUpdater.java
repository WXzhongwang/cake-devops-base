package com.rany.cake.devops.base.service.context;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class ProgressUpdater implements ProgressObserver {

    private final StringRedisTemplate stringRedisTemplate;

    public ProgressUpdater(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void updateProgress(DeployContext deployContext) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(deployContext.getProgress().getPipeKey(), deployContext.dump());
    }
}
