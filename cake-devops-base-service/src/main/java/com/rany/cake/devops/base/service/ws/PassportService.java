package com.rany.cake.devops.base.service.ws;

import com.alibaba.fastjson.JSON;
import com.rany.cake.devops.base.api.dto.user.LoginBindDTO;
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

    public void setUserToken(SsoUser ssoUser) {
        // 设置登陆信息
        long timestamp = System.currentTimeMillis();
        String loginToken = ValueMix.createLoginToken(ssoUser.getUserId(), timestamp);
        UserDTO userCache = new UserDTO();
        userCache.setUserId(ssoUser.getUserId());
        userCache.setUsername(ssoUser.getUserName());
        userCache.setRealName(ssoUser.getRealName());
        userCache.setTimestamp(timestamp);
        // 设置绑定信息
        LoginBindDTO bind = new LoginBindDTO();
        bind.setTimestamp(timestamp);
        // bind.setLoginIp(ip);
        // 设置登陆缓存
        long expire = Long.parseLong(SystemEnvAttr.LOGIN_TOKEN_EXPIRE.getValue());
        String userInfoKey = Strings.format(KeyConst.LOGIN_TOKEN_KEY, ssoUser.getUserId());
        redisTemplate.opsForValue().set(userInfoKey, JSON.toJSONString(userCache), expire, TimeUnit.HOURS);
        // 设置绑定缓存
        String loginBindKey = Strings.format(KeyConst.LOGIN_TOKEN_BIND_KEY, ssoUser.getUserId(), timestamp);
        redisTemplate.opsForValue().set(loginBindKey, JSON.toJSONString(bind), expire, TimeUnit.HOURS);
    }

    public UserDTO getUserByToken(String token, String checkIp) {
        if (Strings.isBlank(token)) {
            return null;
        }
        // 解析 token 信息
        Long[] info = ValueMix.getLoginTokenInfo(token);
        if (info == null) {
            return null;
        }
        Long userId = info[0];
        Long timestamp = info[1];
        // 获取用户绑定信息
        String bindCache = redisTemplate.opsForValue().get(Strings.format(KeyConst.LOGIN_TOKEN_BIND_KEY, userId, timestamp));
        if (Strings.isBlank(bindCache)) {
            return null;
        }
        // 获取用户登陆信息
        String userCache = redisTemplate.opsForValue().get(Strings.format(KeyConst.LOGIN_TOKEN_KEY, userId));
        if (Strings.isBlank(userCache)) {
            return null;
        }
        LoginBindDTO bind = JSON.parseObject(bindCache, LoginBindDTO.class);
        UserDTO user = JSON.parseObject(userCache, UserDTO.class);
        // 检查 ip 是否为登陆时的ip
        if (checkIp != null && !checkIp.equals(bind.getLoginIp())) {
            return null;
        }
        // 检查多端登陆
        if (!EnableType.of(SystemEnvAttr.ALLOW_MULTIPLE_LOGIN.getValue()).getValue()) {
            // 不是登陆的时间戳则证明后续有人登陆
            if (!timestamp.equals(user.getTimestamp())) {
                return null;
            }
        }
        user.setCurrentBindTimestamp(bind.getTimestamp());
        return user;
    }
}
