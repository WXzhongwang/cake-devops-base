package com.rany.cake.devops.base.web.config;

import com.rany.cake.devops.base.service.handler.sftp.FileTransferNotifyHandler;
import com.rany.cake.devops.base.service.tail.TailFileHandler;
import com.rany.cake.devops.base.service.ws.TerminalMessageHandler;
import com.rany.cake.devops.base.service.ws.watcher.TerminalWatcherHandler;
import com.rany.cake.devops.base.web.interceptor.FileTransferNotifyInterceptor;
import com.rany.cake.devops.base.web.interceptor.TailFileInterceptor;
import com.rany.cake.devops.base.web.interceptor.TerminalAccessInterceptor;
import com.rany.cake.devops.base.web.interceptor.TerminalWatcherInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

import javax.annotation.Resource;

@Configuration
@EnableWebSocket
public class WebSocketBean implements WebSocketConfigurer {
    @Resource
    private TerminalAccessInterceptor terminalAccessInterceptor;
    @Resource
    private TerminalWatcherInterceptor terminalWatcherInterceptor;
    @Resource
    private TailFileInterceptor tailFileInterceptor;
    @Resource
    private FileTransferNotifyInterceptor fileTransferNotifyInterceptor;
    @Resource
    private TerminalMessageHandler terminalMessageHandler;
    @Resource
    private TerminalWatcherHandler terminalWatcherHandler;
    @Resource
    private FileTransferNotifyHandler fileTransferNotifyHandler;
    @Resource
    private TailFileHandler tailFileHandler;


    @Override
    public void registerWebSocketHandlers(@NotNull WebSocketHandlerRegistry registry) {
        registry.addHandler(terminalMessageHandler, "/cake/keep-alive/machine/terminal/{token}")
                .addInterceptors(terminalAccessInterceptor)
                .setAllowedOrigins("*");
        registry.addHandler(terminalWatcherHandler, "/cake/keep-alive/watcher/terminal/{token}")
                .addInterceptors(terminalWatcherInterceptor)
                .setAllowedOrigins("*");
        registry.addHandler(tailFileHandler, "/cake/keep-alive/tail/{token}")
                .addInterceptors(tailFileInterceptor)
                .setAllowedOrigins("*");
        registry.addHandler(fileTransferNotifyHandler, "/cake/keep-alive/sftp/notify/{token}")
                .addInterceptors(fileTransferNotifyInterceptor)
                .setAllowedOrigins("*");
    }

    /**
     * web socket 缓冲区大小配置
     */
    @Bean
    public ServletServerContainerFactoryBean servletServerContainerFactoryBean() {
        ServletServerContainerFactoryBean factory = new ServletServerContainerFactoryBean();
        factory.setMaxBinaryMessageBufferSize(1024 * 1024);
        factory.setMaxTextMessageBufferSize(1024 * 1024);
        factory.setMaxSessionIdleTimeout(30 * 60000L);
        return factory;
    }
}
