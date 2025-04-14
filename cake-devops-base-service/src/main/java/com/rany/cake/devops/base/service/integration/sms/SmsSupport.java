package com.rany.cake.devops.base.service.integration.sms;

import com.rany.ops.sms.core.entity.SmsConfig;
import com.rany.ops.sms.core.entity.SmsMessage;
import com.rany.ops.sms.core.entity.SmsReceiver;
import com.rany.ops.sms.core.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhongshengwang
 * @version 1.0
 * @date 2025/4/2 18:07
 * @slogon 找到银弹
 */
@Slf4j
@Service
public class SmsSupport {
    @Resource
    private SmsService smsService;


    /**
     * 发送配置
     *
     * @param receiverAddressList 接收人地址
     * @param smsConfig           短信配置
     * @param message             消息内容
     * @param args                参数
     */
    public void sendSms(List<SmsReceiver> receiverAddressList, SmsConfig smsConfig,
                        SmsMessage message, Map<String, String> args) {
        smsService.smsSend(receiverAddressList, smsConfig, message, args);
    }
}
