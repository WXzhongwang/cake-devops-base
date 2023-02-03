package com.rany.cake.devops.base.domain.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/28 20:42
 * @email 18668485565163.com
 */
@Configuration
@ComponentScan({"com.rany.cake.devops.base.domain"})
public class Config {
    @Bean
    public SnowflakeIdWorker snowflakeIdWorker() {
        Random random = new Random(10);
        int workId = random.nextInt(10);
        return new SnowflakeIdWorker(workId, 1);
    }
}
