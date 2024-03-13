package com.rany.cake.devops.base.service.handler.alarm.webhook;

/**
 * webhook 推送器
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/29 18:20
 */
public interface IWebhookPusher {

    /**
     * 执行推送
     */
    void push();

}
