package com.rany.cake.devops.base.service.handler.alarm;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.rany.cake.devops.base.api.dto.AppAccountDTO;
import com.rany.cake.devops.base.domain.base.AppConfig;
import com.rany.cake.devops.base.domain.entity.*;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.AlarmGroupRepository;
import com.rany.cake.devops.base.domain.repository.HostAlarmGroupRepository;
import com.rany.cake.devops.base.domain.repository.WebhookConfigRepository;
import com.rany.cake.devops.base.service.adapter.AppMemberAdapter;
import com.rany.cake.devops.base.service.handler.alarm.push.AlarmWebSideMessagePusher;
import com.rany.cake.devops.base.service.handler.alarm.push.AlarmWebhookPusher;
import com.rany.cake.devops.base.util.enums.AlarmGroupNotifyType;
import com.rany.ops.api.facade.account.AccountFacade;
import com.rany.ops.api.query.account.AccountQuery;
import com.rany.ops.common.dto.account.AccountDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 机器报警执行器
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/29 18:30
 */
@Slf4j
@Component
public class MachineAlarmExecutor {
    @Resource
    private HostAlarmGroupRepository hostAlarmGroupRepository;
    @Resource
    private AlarmGroupRepository alarmGroupRepository;
    @Resource
    private AccountFacade accountFacade;
    @Resource
    private AppConfig appConfig;
    @Resource
    private AppMemberAdapter appMemberAdapter;
    @Resource
    private WebhookConfigRepository webhookConfigRepository;
    @Resource
    private AlarmWebhookPusher alarmWebhookPusher;
    @Resource
    private AlarmWebSideMessagePusher alarmWebSideMessagePusher;


    public void execute(MachineAlarmContext context) {
        log.info("机器触发报警推送 context: {}", JSON.toJSONString(context));
        // 查询报警组
        List<HostAlarmGroup> hostAlarmGroups = hostAlarmGroupRepository.find(new HostId(context.getHostId()));
        List<Long> alarmGroupIdList = hostAlarmGroups
                .stream()
                .map(HostAlarmGroup::getAlarmGroupId)
                .collect(Collectors.toList());
        log.info("机器触发报警推送 groupId: {}", alarmGroupIdList);
        if (alarmGroupIdList.isEmpty()) {
            return;
        }
        List<AlarmGroup> alarmGroupList = alarmGroupRepository.findByGroupIds(alarmGroupIdList);
        // 查询报警组员
        List<Long> alarmUserIdList = new ArrayList<>();
        for (AlarmGroup alarmGroup : alarmGroupList) {
            for (AlarmGroupUser user : alarmGroup.getUsers()) {
                alarmUserIdList.add(user.getAccountId());
            }
        }

        log.info("机器触发报警推送 userId: {}", alarmUserIdList);
        if (alarmGroupIdList.isEmpty()) {
            return;
        }

        AccountQuery accountQuery = new AccountQuery();
        accountQuery.setTenantId(appConfig.getTenantId());
        accountQuery.setAccountIds(alarmUserIdList);
        List<AccountDTO> accountsList = accountFacade.findAccounts(accountQuery);
        List<AppAccountDTO> appAccountDTOList = appMemberAdapter.toDTO(accountsList);
        Map<String, AppAccountDTO> userMapping = Maps.uniqueIndex(appAccountDTOList, AppAccountDTO::getId);

        context.setUserMapping(userMapping);
        // 通知站内信
        alarmWebSideMessagePusher.push(context);

        // 通知webhook
        for (AlarmGroup alarmGroup : alarmGroupList) {
            for (AlarmGroupNotify alarmNotify : alarmGroup.getNotifies()) {
                Integer notifyType = alarmNotify.getNotifyType();
                if (AlarmGroupNotifyType.WEBHOOK.getType().equals(notifyType)) {
                    WebhookConfig webhookConfig = webhookConfigRepository.find(alarmNotify.getNotifyId());
                    context.setWebhookConfig(webhookConfig);
                    // 通知 webhook
                    try {
                        alarmWebhookPusher.push(context);
                    } catch (Exception e) {
                        log.error("机器报警 webhook 推送失败 id: {}", alarmNotify.getNotifyId(), e);
                    }
                }
            }
        }
    }
}
