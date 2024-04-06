package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.Page;
import com.google.common.collect.Maps;
import com.rany.cake.devops.base.api.command.alarm.CreateAlarmGroupCommand;
import com.rany.cake.devops.base.api.command.alarm.DeleteAlarmGroupCommand;
import com.rany.cake.devops.base.api.command.alarm.ModifyAlarmGroupCommand;
import com.rany.cake.devops.base.api.dto.AlarmGroupDTO;
import com.rany.cake.devops.base.api.dto.AlarmGroupNotifyDTO;
import com.rany.cake.devops.base.api.dto.AlarmGroupUserDTO;
import com.rany.cake.devops.base.api.dto.AppAccountDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.api.query.AlarmGroupBasicQuery;
import com.rany.cake.devops.base.api.query.AlarmGroupPageQuery;
import com.rany.cake.devops.base.api.service.AlarmGroupService;
import com.rany.cake.devops.base.domain.base.AppConfig;
import com.rany.cake.devops.base.domain.entity.AlarmGroup;
import com.rany.cake.devops.base.domain.entity.AlarmGroupNotify;
import com.rany.cake.devops.base.domain.entity.AlarmGroupUser;
import com.rany.cake.devops.base.domain.entity.WebhookConfig;
import com.rany.cake.devops.base.domain.repository.AlarmGroupRepository;
import com.rany.cake.devops.base.domain.repository.WebhookConfigRepository;
import com.rany.cake.devops.base.domain.repository.param.AlarmGroupQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.AlarmGroupDataAdapter;
import com.rany.cake.devops.base.service.adapter.AppMemberAdapter;
import com.rany.cake.devops.base.util.enums.AlarmGroupNotifyType;
import com.rany.uic.api.facade.account.AccountFacade;
import com.rany.uic.api.query.account.AccountQuery;
import com.rany.uic.common.dto.account.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@ShenyuService("/alarm-group/**")
@Slf4j
@AllArgsConstructor
public class AlarmGroupRemoteService implements AlarmGroupService {

    private final AlarmGroupRepository alarmGroupRepository;
    private final AlarmGroupDataAdapter alarmGroupDataAdapter;
    private final AccountFacade accountFacade;
    private final AppConfig appConfig;
    private final AppMemberAdapter appMemberAdapter;
    private final WebhookConfigRepository webhookConfigRepository;

    @Override
    public String createAlarmGroup(CreateAlarmGroupCommand command) {
        AlarmGroup alarmGroup = new AlarmGroup();
        alarmGroup.setGroupName(command.getGroupName());
        alarmGroup.setGroupDescription(command.getGroupDescription());
        alarmGroup.init(command.getUser());
        List<AlarmGroupUser> users = new ArrayList<>();
        for (String accountId : command.getAccountIds()) {
            AlarmGroupUser user = new AlarmGroupUser();
            user.init(command.getUser());
            user.setAccountId(Long.valueOf(accountId));
            users.add(user);
        }
        alarmGroup.setUsers(users);
        List<AlarmGroupNotify> notifies = new ArrayList<>();
        for (Long notifyId : command.getNotifyIdList()) {
            AlarmGroupNotify notify = new AlarmGroupNotify();
            notify.init(command.getUser());
            notify.setNotifyId(notifyId);
            notify.setNotifyType(AlarmGroupNotifyType.WEBHOOK.getType());
            notifies.add(notify);
        }
        alarmGroup.setNotifies(notifies);
        alarmGroupRepository.save(alarmGroup);
        return alarmGroup.getId().toString();
    }

    @Override
    public Boolean modifyAlarmGroup(ModifyAlarmGroupCommand command) {
        AlarmGroup alarmGroup = alarmGroupRepository.find(command.getAlarmGroupId());
        if (alarmGroup == null) {
            throw new DevOpsException(DevOpsErrorMessage.ALARM_GROUP_NOT_FOUND);
        }
        alarmGroup.setGroupName(command.getGroupName());
        alarmGroup.setGroupDescription(command.getGroupDescription());
        alarmGroup.modify(command.getUser());
        List<AlarmGroupUser> users = new ArrayList<>();
        for (String accountId : command.getAccountIds()) {
            AlarmGroupUser user = new AlarmGroupUser();
            user.init(command.getUser());
            user.setAccountId(Long.valueOf(accountId));
            users.add(user);
        }
        alarmGroup.setUsers(users);
        List<AlarmGroupNotify> notifies = new ArrayList<>();
        for (Long notifyId : command.getNotifyIdList()) {
            AlarmGroupNotify notify = new AlarmGroupNotify();
            notify.init(command.getUser());
            notify.setNotifyId(notifyId);
            notify.setNotifyType(AlarmGroupNotifyType.WEBHOOK.getType());
            notifies.add(notify);
        }
        alarmGroup.setNotifies(notifies);
        alarmGroupRepository.update(alarmGroup);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteAlarmGroup(DeleteAlarmGroupCommand command) {
        AlarmGroup alarmGroup = alarmGroupRepository.find(command.getAlarmGroupId());
        if (alarmGroup == null) {
            throw new DevOpsException(DevOpsErrorMessage.ALARM_GROUP_NOT_FOUND);
        }
        alarmGroup.delete(command.getUser());
        alarmGroupRepository.remove(alarmGroup);
        return Boolean.TRUE;
    }

    @Override
    public AlarmGroupDTO getAlarmGroup(AlarmGroupBasicQuery basicQuery) {
        AlarmGroup alarmGroup = alarmGroupRepository.find(basicQuery.getAlarmGroupId());
        if (alarmGroup == null) {
            throw new DevOpsException(DevOpsErrorMessage.ALARM_GROUP_NOT_FOUND);
        }
        return alarmGroupDataAdapter.sourceToTarget(alarmGroup);
    }

    @Override
    public Page<AlarmGroupDTO> pageAlarmGroup(AlarmGroupPageQuery pageQuery) {
        AlarmGroupQueryParam alarmGroupQueryParam = alarmGroupDataAdapter.convertParam(pageQuery);
        Page<AlarmGroup> page = alarmGroupRepository.page(alarmGroupQueryParam);
        Collection<AlarmGroup> items = page.getItems();
        List<AlarmGroupDTO> webHookConfigDTOS = alarmGroupDataAdapter.sourceToTarget(new ArrayList<>(items));
        for (AlarmGroupDTO webHookConfigDTO : webHookConfigDTOS) {
            List<AlarmGroupUserDTO> users = webHookConfigDTO.getUsers();
            // 转换人员名称
            List<Long> alarmUserIdList = users.stream().map(p -> NumberUtils.toLong(p.getAccountId())).collect(Collectors.toList());
            AccountQuery accountQuery = new AccountQuery();
            accountQuery.setTenantId(appConfig.getTenantId());
            accountQuery.setAccountIds(alarmUserIdList);
            ListResult<AccountDTO> accountsList = accountFacade.findAccounts(accountQuery);
            List<AccountDTO> accountDTOList = accountsList.getContent();
            List<AppAccountDTO> appAccountDTOList = appMemberAdapter.toDTO(accountDTOList);
            Map<String, AppAccountDTO> userMapping = Maps.uniqueIndex(appAccountDTOList, AppAccountDTO::getId);
            for (AlarmGroupUserDTO user : users) {
                AppAccountDTO appAccountDTO = userMapping.get(user.getAccountId());
                if (appAccountDTO != null) {
                    user.setUsername(appAccountDTO.getAccountName());
                }
            }
            List<AlarmGroupNotifyDTO> notifies = webHookConfigDTO.getNotifies();
            List<Long> webhookIds = notifies.stream().map(AlarmGroupNotifyDTO::getNotifyId).collect(Collectors.toList());
            List<WebhookConfig> webhookConfigs = webhookConfigRepository.findByIds(webhookIds);
            Map<Long, WebhookConfig> webhookConfigMap = Maps.uniqueIndex(webhookConfigs, WebhookConfig::getId);
            for (AlarmGroupNotifyDTO notify : notifies) {
                WebhookConfig webhookConfig = webhookConfigMap.get(notify.getNotifyId());
                if (webhookConfig != null) {
                    notify.setWebHookName(webhookConfig.getWebhookName());
                }
            }
        }
        return PageUtils.build(page, webHookConfigDTOS);
    }
}
