package com.rany.cake.devops.base.service.context;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class ProgressUpdater implements ProgressObserver {

    private final RedisTemplate<String, String> redisTemplate;

    public ProgressUpdater(RedisTemplate<String, String> stringRedisTemplate) {
        this.redisTemplate = stringRedisTemplate;
    }

    @Override
    public void updateProgress(DeployContext deployContext) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(deployContext.getProgress().getPipeKey(), deployContext.dump());
    }
}
