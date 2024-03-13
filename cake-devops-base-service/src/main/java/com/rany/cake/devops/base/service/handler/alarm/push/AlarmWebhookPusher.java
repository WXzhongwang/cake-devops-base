package com.rany.cake.devops.base.service.handler.alarm.push;


import com.rany.cake.devops.base.api.dto.AppAccountDTO;
import com.rany.cake.devops.base.domain.entity.WebhookConfig;
import com.rany.cake.devops.base.service.handler.alarm.MachineAlarmContext;
import com.rany.cake.devops.base.service.handler.alarm.webhook.DingRobotPusher;
import com.rany.cake.devops.base.util.EventKeys;
import com.rany.cake.devops.base.util.ResourceLoader;
import com.rany.cake.devops.base.util.enums.MachineAlarmType;
import com.rany.cake.devops.base.util.enums.WebHookType;
import com.rany.cake.toolkit.lang.math.Numbers;
import com.rany.cake.toolkit.lang.time.Dates;
import com.rany.cake.toolkit.lang.utils.Maps;
import com.rany.cake.toolkit.lang.utils.Strings;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 机器报警 webhook 推送
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/29 18:41
 */
@Component
public class AlarmWebhookPusher implements IAlarmPusher {

    private static final String DING_TEMPLATE = "/templates/push/machine-alarm-ding.template";

    @Override
    public void push(MachineAlarmContext context) {
        // 查询 webhook
        WebhookConfig webhook = context.getWebhookConfig();
        if (webhook == null) {
            return;
        }
        // 触发 webhook
        if (WebHookType.DINGDING.getType().equals(webhook.getWebhookType())) {
            // 钉钉机器人
            Map<String, Object> params = Maps.newMap();
            params.put(EventKeys.NAME, context.getMachineName());
            params.put(EventKeys.HOST, context.getMachineHost());
            params.put(EventKeys.VALUE, Numbers.setScale(context.getAlarmValue(), 2));
            params.put(EventKeys.TYPE, MachineAlarmType.of(context.getAlarmType()).getLabel());
            params.put(EventKeys.TIME, Dates.format(context.getAlarmTime()));
            String text = Strings.format(ResourceLoader.get(DING_TEMPLATE, AlarmWebhookPusher.class), params);
            // @ 的用户
            List<String> atMobiles = context.getUserMapping()
                    .values()
                    .stream()
                    .map(AppAccountDTO::getPhone)
                    .collect(Collectors.toList());
            // 推送
            DingRobotPusher.builder()
                    .url(webhook.getWebhookUrl())
                    .title("机器发生报警")
                    .text(text)
                    .atMobiles(atMobiles)
                    .build()
                    .push();
        }
    }
}
