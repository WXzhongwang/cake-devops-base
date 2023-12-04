package com.rany.cake.devops.base.service.lock.core.strategy;


import com.rany.cake.devops.base.service.lock.prop.RedissonProperties;
import org.redisson.config.Config;

/**
 * Redisson配置构建接口
 *
 * @author zhongshengwang
 */
public interface RedissonConfigStrategy {

    /**
     * 根据不同的Redis配置策略创建对应的Config
     *
     * @param redissonProperties 配置信息
     * @return Config
     */
    Config createRedissonConfig(RedissonProperties redissonProperties);
}
