package com.rany.cake.devops.base.service.integration.code;


import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RedisSerialNumberGenerator {

    private final StringRedisTemplate redisTemplate;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static final DateTimeFormatter TODAY = DateTimeFormatter.ofPattern("yyyy_MM_dd");
    private static final String RELEASE_NO_KEY = "cake_release_serial";

    public RedisSerialNumberGenerator(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 应用日发布分支流水号
     *
     * @param key 应用名
     * @return
     */
    public String generateSerialNumber(String key) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        LocalDateTime now = LocalDateTime.now();
        String format = FORMATTER.format(now);
        String todayKey = String.format("%s_%s", key, TODAY.format(now));
        // 生成自增部分
        Long serialNumber = valueOperations.increment(todayKey, 1);
        // 拼接流水号
        return format + String.format("%04d", serialNumber); // 这里"%04d"表示生成4位的自增部分，可根据需求调整
    }

    /**
     * 发布单号
     * R202311242000000001
     *
     * @return
     */
    public String generateReleaseSerialNumber() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        LocalDateTime now = LocalDateTime.now();
        String format = FORMATTER.format(now);
        // 生成自增部分
        Long serialNumber = valueOperations.increment(RELEASE_NO_KEY, 1);
        // 拼接流水号
        return "R" + format + String.format("%04d", serialNumber); // 这里"%04d"表示生成4位的自增部分，可根据需求调整
    }


    /**
     * 发布pipeline流水线号
     *
     * @param pipeKey 发布单号
     * @return 发布pipeline流水线号
     */
    public String generatePipeNumber(String pipeKey) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        LocalDateTime now = LocalDateTime.now();
        String format = FORMATTER.format(now);
        String todayKey = String.format("%s_%s", pipeKey, TODAY.format(now));
        // 生成自增部分
        Long serialNumber = valueOperations.increment(todayKey, 1);
        // 拼接流水号
        return format + String.format("%04d", serialNumber); // 这里"%04d"表示生成4位的自增部分，可根据需求调整
    }
}
