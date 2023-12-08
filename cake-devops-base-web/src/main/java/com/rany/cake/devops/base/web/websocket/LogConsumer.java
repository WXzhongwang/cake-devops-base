package com.rany.cake.devops.base.web.websocket;


import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
@Component
public class LogConsumer {
    @Resource
    private WebSocketService webSocketService;

    @RabbitHandler
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "${devops.log.queueName}", durable = "true"),
                    exchange = @Exchange(name = "${devops.log.exchangeName}", ignoreDeclarationExceptions = "true"),
                    key = "${devops.log.bindingKey}"
            ),
            concurrency = "1"
    )
    public void listenerPush(String msg, Channel channel, Message message) {
        try {
            log.debug("consumer>>>接收到的消息>>>{}", msg);
            msg.split(" - ")[0].trim().replace("[", "").replace("]", "");
            String releaseId = msg.substring(0, msg.indexOf(" - ")).trim().replace("[", "").replace("]", "");
            msg = msg.substring(msg.indexOf(" - ") + 2);
            //调用websocket发送日志信息到页面上
            webSocketService.sendMessage(releaseId, msg);
        } catch (Exception e) {
            log.error("获取消息失败，异常原因：{}", e.getMessage(), e);
        } finally {
            try {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}