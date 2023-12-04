package com.rany.cake.devops.base.service.lock.core;


import com.google.common.base.Preconditions;
import com.rany.cake.devops.base.service.lock.core.strategy.RedissonConfigStrategy;
import com.rany.cake.devops.base.service.lock.core.strategy.impl.ClusterRedissonConfigStrategyImpl;
import com.rany.cake.devops.base.service.lock.core.strategy.impl.MasterSlaveRedissonConfigStrategyImpl;
import com.rany.cake.devops.base.service.lock.core.strategy.impl.SentinelRedissonConfigStrategyImpl;
import com.rany.cake.devops.base.service.lock.core.strategy.impl.StandaloneRedissonConfigStrategyImpl;
import com.rany.cake.devops.base.service.lock.enums.RedisConnectionType;
import com.rany.cake.devops.base.service.lock.prop.RedissonProperties;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.config.Config;


/**
 * Redisson配置管理器，用于初始化的redisson实例
 *
 * @author zhongshengwang
 */
@Slf4j
@Getter
public class RedissonManager {

    private Redisson redisson = null;

    public RedissonManager(RedissonProperties redissonProperties) {
        // 装配开关
        Boolean enabled = redissonProperties.getEnabled();
        if (enabled) {
            try {
                final Config config = RedissonConfigFactory.getInstance().createConfig(redissonProperties);
                redisson = (Redisson) Redisson.create(config);
            } catch (Exception e) {
                log.error("Redisson初始化错误", e);
            }
        }
    }

    /**
     * Redisson连接方式配置工厂
     * 双重检查锁
     */
    static class RedissonConfigFactory {

        private RedissonConfigFactory() {
        }

        private static volatile RedissonConfigFactory factory = null;

        public static RedissonConfigFactory getInstance() {
            if (factory == null) {
                synchronized (Object.class) {
                    if (factory == null) {
                        factory = new RedissonConfigFactory();
                    }
                }
            }
            return factory;
        }

        /**
         * 根据连接类型創建连接方式的配置
         *
         * @param redissonProperties 配置信息
         * @return Config
         */
        Config createConfig(RedissonProperties redissonProperties) {
            Preconditions.checkNotNull(redissonProperties);
            Preconditions.checkNotNull(redissonProperties.getAddress(), "redis地址未配置");
            RedisConnectionType connectionType = redissonProperties.getType();
            // 声明连接方式
            RedissonConfigStrategy redissonConfigStrategy;
            if (connectionType.equals(RedisConnectionType.SENTINEL)) {
                redissonConfigStrategy = new SentinelRedissonConfigStrategyImpl();
            } else if (connectionType.equals(RedisConnectionType.CLUSTER)) {
                redissonConfigStrategy = new ClusterRedissonConfigStrategyImpl();
            } else if (connectionType.equals(RedisConnectionType.MASTERSLAVE)) {
                redissonConfigStrategy = new MasterSlaveRedissonConfigStrategyImpl();
            } else {
                redissonConfigStrategy = new StandaloneRedissonConfigStrategyImpl();
            }
            Preconditions.checkNotNull(redissonConfigStrategy, "连接方式创建异常");

            return redissonConfigStrategy.createRedissonConfig(redissonProperties);
        }
    }


}
