package com.rany.cake.devops.base.service.ws;

import com.alibaba.fastjson.JSON;
import com.rany.cake.devops.base.api.dto.user.UserDTO;
import com.rany.cake.devops.base.service.base.ValueMix;
import com.rany.cake.devops.base.util.KeyConst;
import com.rany.cake.devops.base.util.enums.EnableType;
import com.rany.cake.devops.base.util.system.SystemEnvAttr;
import com.rany.cake.dingtalk.sdk.beans.SsoUser;
import com.rany.cake.toolkit.lang.utils.Strings;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class PassportService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public String getTerminalToken(SsoUser ssoUser, String remoteIp) {
        // 设置登陆信息
        long timestamp = System.currentTimeMillis();
        String loginToken = ValueMix.createLoginToken(ssoUser.getUserId(), timestamp);
        UserDTO userCache = new UserDTO();
        userCache.setUserId(ssoUser.getUserId());
        userCache.setUsername(ssoUser.getUserName());
        userCache.setRealName(ssoUser.getRealName());
        userCache.setTimestamp(timestamp);
        userCache.setBindIp(remoteIp);
        // 设置登陆缓存
        long expire = Long.parseLong(SystemEnvAttr.LOGIN_TOKEN_EXPIRE.getValue());
        String userInfoKey = Strings.format(KeyConst.LOGIN_TOKEN_KEY, ssoUser.getUserId());
        redisTemplate.opsForValue().set(userInfoKey, JSON.toJSONString(userCache), expire, TimeUnit.HOURS);
        return loginToken;
    }

    public UserDTO getUserByTerminalToken(String token, String checkIp) {
        if (Strings.isBlank(token)) {
            return null;
        }
        // 解析 token 信息 包含用户及时间戳信息
        Long[] info = ValueMix.getLoginTokenInfo(token);
        if (info == null) {
            return null;
        }
        Long userId = info[0];
        Long timestamp = info[1];
        // 获取用户登陆信息
        String userCache = redisTemplate.opsForValue().get(Strings.format(KeyConst.LOGIN_TOKEN_KEY, userId));
        if (Strings.isBlank(userCache)) {
            return null;
        }
        UserDTO user = JSON.parseObject(userCache, UserDTO.class);
        // 检查 ip 是否为登陆时的ip
        if (checkIp != null && !checkIp.equals(user.getBindIp())) {
            return null;
        }
        // 检查多端登陆
        if (!EnableType.of(SystemEnvAttr.ALLOW_MULTIPLE_LOGIN.getValue()).getValue()) {
            // 不是登陆的时间戳则证明后续有人登陆
            if (!timestamp.equals(user.getTimestamp())) {
                return null;
            }
        }
        user.setCurrentBindTimestamp(user.getTimestamp());
        return user;
    }
}
