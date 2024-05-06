package com.rany.cake.devops.base.web.config;

import com.rany.cake.devops.base.web.interceptor.TerminalAccessInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

@Configuration
@EnableWebSocket
public class WebSocketBean implements WebSocketConfigurer {
    @Resource
    private TerminalAccessInterceptor terminalAccessInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

    }
}
