package com.rany.cake.devops.base.service.handler.alarm.push;


import com.rany.cake.devops.base.domain.entity.WebSideMessage;
import com.rany.cake.devops.base.domain.repository.WebSideMessageRepository;
import com.rany.cake.devops.base.service.handler.alarm.MachineAlarmContext;
import com.rany.cake.devops.base.util.EventKeys;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.util.enums.MachineAlarmType;
import com.rany.cake.devops.base.util.enums.message.MessageType;
import com.rany.cake.devops.base.util.enums.message.ReadStatus;
import com.rany.cake.toolkit.lang.convert.Converts;
import com.rany.cake.toolkit.lang.math.Numbers;
import com.rany.cake.toolkit.lang.time.Dates;
import com.rany.cake.toolkit.lang.utils.Maps;
import com.rany.cake.toolkit.lang.utils.Strings;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 机器报警站内信推送
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/29 18:41
 */
@Component
public class AlarmWebSideMessagePusher implements IAlarmPusher {

    @Resource
    private WebSideMessageRepository webSideMessageRepository;

    public void push(MachineAlarmContext context) {
        // 发送站内信
        Map<String, Object> params = Maps.newMap();
        params.put(EventKeys.NAME, context.getMachineName());
        params.put(EventKeys.HOST, context.getMachineHost());
        params.put(EventKeys.TYPE, MachineAlarmType.of(context.getAlarmType()).getLabel());
        params.put(EventKeys.VALUE, Numbers.setScale(context.getAlarmValue(), 2));
        params.put(EventKeys.TIME, Dates.format(context.getAlarmTime()));
        context.getUserMapping().forEach((k, v) -> {
            WebSideMessage message = new WebSideMessage();
            message.setMessageClassify(Converts.toByte(MessageType.MACHINE_ALARM.getClassify().getClassify()));
            message.setMessageType(MessageType.MACHINE_ALARM.getType());
            message.setReadStatus(Converts.toByte(ReadStatus.UNREAD.getStatus()));
            message.setToUserId(k);
            message.setToUserName(v.getAccountName());
            message.setSendMessage(Strings.format(MessageType.MACHINE_ALARM.getTemplate(), params));
            message.setRelId(context.getMachineId());
            message.setIsDeleted(DeleteStatusEnum.NO.getValue());
            webSideMessageRepository.save(message);
        });
    }

}
