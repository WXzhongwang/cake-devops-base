package com.rany.cake.devops.base.service.handler.sftp;

import com.alibaba.fastjson.JSON;
import com.cake.framework.common.exception.BusinessException;
import com.rany.cake.devops.base.api.dto.SftpSessionTokenDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.util.KeyConst;
import com.rany.cake.toolkit.lang.id.UUIds;
import com.rany.cake.toolkit.lang.utils.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SftpInternalService {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public String createSessionToken(String userId, String machineId) {
        // 生成token
        String sessionToken = UUIds.random15();
        // 设置缓存
        String key = Strings.format(KeyConst.SFTP_SESSION_TOKEN, sessionToken);
        SftpSessionTokenDTO info = new SftpSessionTokenDTO();
        info.setUserId(userId);
        info.setHostId(machineId);
        // 12h
        redisTemplate.opsForValue().set(key, JSON.toJSONString(info),
                KeyConst.SFTP_SESSION_EXPIRE, TimeUnit.SECONDS);
        return sessionToken;
    }

    public SftpSessionTokenDTO getTokenInfo(String sessionToken) {
        if (Strings.isBlank(sessionToken)) {
            throw new BusinessException(DevOpsErrorMessage.SFTP_TOKEN_EMPTY);
        }
        String key = Strings.format(KeyConst.SFTP_SESSION_TOKEN, sessionToken);
        String value = redisTemplate.opsForValue().get(key);
        if (Strings.isBlank(value)) {
            throw new BusinessException(DevOpsErrorMessage.SFTP_TOKEN_EMPTY);
        }
        return JSON.parseObject(value, SftpSessionTokenDTO.class);
    }


}
