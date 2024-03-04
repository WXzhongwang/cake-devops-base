package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.alarm.CreateAlarmGroupCommand;
import com.rany.cake.devops.base.api.command.alarm.DeleteAlarmGroupCommand;
import com.rany.cake.devops.base.api.command.alarm.ModifyAlarmGroupCommand;
import com.rany.cake.devops.base.api.dto.AlarmGroupDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.api.query.AlarmGroupBasicQuery;
import com.rany.cake.devops.base.api.query.AlarmGroupPageQuery;
import com.rany.cake.devops.base.api.service.AlarmGroupService;
import com.rany.cake.devops.base.domain.entity.AlarmGroup;
import com.rany.cake.devops.base.domain.entity.AlarmGroupNotify;
import com.rany.cake.devops.base.domain.entity.AlarmGroupUser;
import com.rany.cake.devops.base.domain.repository.AlarmGroupRepository;
import com.rany.cake.devops.base.domain.repository.param.AlarmGroupQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.AlarmGroupDataAdapter;
import com.rany.cake.devops.base.util.enums.AlarmGroupNotifyType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@ShenyuService("/alarm-group/**")
@Slf4j
@AllArgsConstructor
public class AlarmGroupRemoteService implements AlarmGroupService {

    private AlarmGroupRepository alarmGroupRepository;
    private AlarmGroupDataAdapter alarmGroupDataAdapter;

    @Override
    public PojoResult<String> createAlarmGroup(CreateAlarmGroupCommand command) {
        AlarmGroup alarmGroup = new AlarmGroup();
        alarmGroup.setGroupName(command.getGroupName());
        alarmGroup.setGroupDescription(command.getGroupDescription());
        for (Long accountId : command.getAccountIds()) {
            AlarmGroupUser user = new AlarmGroupUser();
            user.setAccountId(accountId);
        }
        for (Long notifyId : command.getNotifyIdList()) {
            AlarmGroupNotify user = new AlarmGroupNotify();
            user.setNotifyId(notifyId);
            user.setNotifyType(AlarmGroupNotifyType.WEBHOOK.getType());
        }
        alarmGroupRepository.save(alarmGroup);
        return PojoResult.succeed(alarmGroup.getId().toString());
    }

    @Override
    public PojoResult<Boolean> modifyAlarmGroup(ModifyAlarmGroupCommand command) {
        AlarmGroup alarmGroup = alarmGroupRepository.find(command.getAlarmGroupId());
        if (alarmGroup == null) {
            throw new DevOpsException(DevOpsErrorMessage.ALARM_GROUP_NOT_FOUND);
        }
        alarmGroup.setGroupName(command.getGroupName());
        alarmGroup.setGroupDescription(command.getGroupDescription());
        alarmGroupRepository.update(alarmGroup);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> deleteAlarmGroup(DeleteAlarmGroupCommand command) {
        AlarmGroup alarmGroup = alarmGroupRepository.find(command.getAlarmGroupId());
        if (alarmGroup == null) {
            throw new DevOpsException(DevOpsErrorMessage.ALARM_GROUP_NOT_FOUND);
        }
        alarmGroupRepository.remove(alarmGroup);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<AlarmGroupDTO> getAlarmGroup(AlarmGroupBasicQuery basicQuery) {
        AlarmGroup alarmGroup = alarmGroupRepository.find(basicQuery.getAlarmGroupId());
        if (alarmGroup == null) {
            throw new DevOpsException(DevOpsErrorMessage.ALARM_GROUP_NOT_FOUND);
        }
        return PojoResult.succeed(alarmGroupDataAdapter.sourceToTarget(alarmGroup));
    }

    @Override
    public PageResult<AlarmGroupDTO> pageAlarmGroup(AlarmGroupPageQuery pageQuery) {
        AlarmGroupQueryParam alarmGroupQueryParam = alarmGroupDataAdapter.convertParam(pageQuery);
        Page<AlarmGroup> page = alarmGroupRepository.page(alarmGroupQueryParam);
        Collection<AlarmGroup> items = page.getItems();
        List<AlarmGroupDTO> webHookConfigDTOS = alarmGroupDataAdapter.sourceToTarget(new ArrayList<>(items));
        Page<AlarmGroupDTO> build = PageUtils.build(page, webHookConfigDTOS);
        return PageResult.succeed(build);
    }
}
