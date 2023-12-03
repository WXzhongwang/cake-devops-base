package com.rany.cake.devops.base.web.config;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.util.WebAppRootListener;

import javax.servlet.ServletContext;

/**
 * 启用WebSocket
 */
@Configuration
public class WebSocketConfiguration implements ServletContextInitializer {

    /**
     * 给spring容器注入这个ServerEndpointExporter对象
     * 相当于xml：
     * 检测所有带有@serverEndpoint注解的bean并注册他们。
     *
     * @return server endpoint exporter
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Override
    public void onStartup(ServletContext servletContext) {
        servletContext.addListener(WebAppRootListener.class);
        /*
         * If the application does not define a MessageHandler.Partial for incoming text messages, any incoming text
         * messages must be buffered so the entire message can be delivered in a single call to the registered MessageHandler.Whole
         * for text messages. The default buffer size for text messages is 8192 bytes. This may be changed for a web application
         * by setting the servlet context initialization parameter org.apache.tomcat.websocket.textBufferSize to the desired value in bytes.
         */
        // 102400
        servletContext.setInitParameter("org.apache.tomcat.websocket.textBufferSize", "20480");
        servletContext.setInitParameter("org.apache.tomcat.websocket.binaryBufferSize", "20480");
        /*
         * 线程池的核心容量大小
         */
        servletContext.setInitParameter("org.apache.tomcat.websocket.executorCoreSize", "30");
        /*
         * 线程池的最大容量大小
         */
        servletContext.setInitParameter("org.apache.tomcat.websocket.executorMaxSize", "400");
    }

}