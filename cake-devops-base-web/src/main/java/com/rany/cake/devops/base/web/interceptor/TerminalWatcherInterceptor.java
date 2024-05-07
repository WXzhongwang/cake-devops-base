package com.rany.cake.devops.base.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.rany.cake.devops.base.api.dto.TerminalWatcherDTO;
import com.rany.cake.devops.base.service.base.WebSockets;
import com.rany.cake.devops.base.util.KeyConst;
import com.rany.cake.toolkit.lang.utils.Strings;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.annotation.Resource;
import java.util.Map;


/**
 * terminal 监视拦截器
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/7/29 10:48
 */
@Component
@Slf4j
public class TerminalWatcherInterceptor implements HandshakeInterceptor {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean beforeHandshake(@NotNull ServerHttpRequest request,
                                   @NotNull ServerHttpResponse response,
                                   @NotNull WebSocketHandler wsHandler,
                                   @NotNull Map<String, Object> attributes) {
        // 获取 token
        String token = WebSockets.getToken(request);
        String tokenKey = Strings.format(KeyConst.TERMINAL_WATCHER_TOKEN, token);
        String tokenValue = redisTemplate.opsForValue().get(tokenKey);
        boolean access = false;
        if (!Strings.isBlank(tokenValue)) {
            access = true;
            TerminalWatcherDTO watcher = JSON.parseObject(tokenValue, TerminalWatcherDTO.class);
            attributes.put(WebSockets.UID, watcher.getUserId());
            attributes.put(WebSockets.TOKEN, watcher.getToken());
            attributes.put(WebSockets.READONLY, watcher.getReadonly());
            // 删除 token
            redisTemplate.delete(tokenKey);
        }
        log.info("terminal尝试监视ws连接开始 token: {}, 结果: {}", token, access);
        return access;
    }

    @Override
    public void afterHandshake(@NotNull ServerHttpRequest request,
                               @NotNull ServerHttpResponse response,
                               @NotNull WebSocketHandler wsHandler,
                               Exception exception) {
    }

}
