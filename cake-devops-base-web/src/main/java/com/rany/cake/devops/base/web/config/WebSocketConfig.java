package com.rany.cake.devops.base.web.config;

import com.rany.cake.devops.base.service.handler.sftp.FileTransferNotifyHandler;
import com.rany.cake.devops.base.service.tail.TailFileHandler;
import com.rany.cake.devops.base.service.ws.TerminalMessageHandler;
import com.rany.cake.devops.base.service.ws.watcher.TerminalWatcherHandler;
import com.rany.cake.devops.base.web.interceptor.FileTransferNotifyInterceptor;
import com.rany.cake.devops.base.web.interceptor.TailFileInterceptor;
import com.rany.cake.devops.base.web.interceptor.TerminalAccessInterceptor;
import com.rany.cake.devops.base.web.interceptor.TerminalWatcherInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

@Configuration
@EnableWebSocket
// @AutoConfigureAfter(value = WebMvcConfig.class)
public class WebSocketConfig implements WebSocketConfigurer {
    @Autowired
    private TerminalAccessInterceptor terminalAccessInterceptor;
    @Autowired
    private TerminalWatcherInterceptor terminalWatcherInterceptor;
    @Autowired
    private TailFileInterceptor tailFileInterceptor;
    @Autowired
    private FileTransferNotifyInterceptor fileTransferNotifyInterceptor;
    @Autowired
    private TerminalMessageHandler terminalMessageHandler;
    @Autowired
    private TerminalWatcherHandler terminalWatcherHandler;
    @Autowired
    private FileTransferNotifyHandler fileTransferNotifyHandler;
    @Autowired
    private TailFileHandler tailFileHandler;
    @Resource
    private TestMessageHandler testMessageHandler;
    @Resource
    private TestMessageInterceptor testMessageInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(testMessageHandler, "/api/ws")
//                .addInterceptors(testMessageInterceptor)
//                .setAllowedOrigins("*");

        registry.addHandler(terminalMessageHandler, "/api/keep-alive/machine/terminal/{token}")
                .addInterceptors(terminalAccessInterceptor)
                .setAllowedOrigins("*");
        registry.addHandler(terminalWatcherHandler, "/api/keep-alive/watcher/terminal/{token}")
                .addInterceptors(terminalWatcherInterceptor)
                .setAllowedOrigins("*");
        registry.addHandler(tailFileHandler, "/api/keep-alive/tail/{token}")
                .addInterceptors(tailFileInterceptor)
                .setAllowedOrigins("*");
        registry.addHandler(fileTransferNotifyHandler, "/api/keep-alive/sftp/notify/{token}")
                .addInterceptors(fileTransferNotifyInterceptor)
                .setAllowedOrigins("*");
    }


}
