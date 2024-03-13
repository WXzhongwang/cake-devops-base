package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.host.alarm.SetHostAlarmConfigCommand;
import com.rany.cake.devops.base.api.command.host.alarm.SetHostAlarmGroupCommand;
import com.rany.cake.devops.base.api.dto.HostAlarmConfigDTO;
import com.rany.cake.devops.base.api.dto.HostAlarmConfigWrapperDTO;
import com.rany.cake.devops.base.api.dto.HostAlarmGroupDTO;
import com.rany.cake.devops.base.api.service.HostAlarmConfigService;
import com.rany.cake.devops.base.domain.entity.HostAlarmConfig;
import com.rany.cake.devops.base.domain.entity.HostAlarmGroup;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.HostAlarmConfigRepository;
import com.rany.cake.devops.base.domain.repository.HostAlarmGroupRepository;
import com.rany.cake.devops.base.service.adapter.HostAlarmConfigDataAdapter;
import com.rany.cake.devops.base.service.adapter.HostAlarmGroupDataAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;

import java.util.ArrayList;
import java.util.List;

@Service
@ShenyuService("/host-alarm-config/**")
@Slf4j
@AllArgsConstructor
public class HostAlarmConfigRemoteService implements HostAlarmConfigService {

    private final HostAlarmConfigRepository hostAlarmConfigRepository;
    private final HostAlarmConfigDataAdapter hostAlarmConfigDataAdapter;
    private final HostAlarmGroupDataAdapter hostAlarmGroupDataAdapter;
    private final HostAlarmGroupRepository hostAlarmGroupRepository;

    @Override
    public PojoResult<Boolean> setHostAlarmConfig(SetHostAlarmConfigCommand command) {
        hostAlarmConfigRepository.deleteAlarmConfig(command.getHostId(), command.getAlarmType());
        HostAlarmConfig hostAlarmConfig = new HostAlarmConfig();
        hostAlarmConfig.setHostId(command.getHostId());
        hostAlarmConfig.setAlarmType(command.getAlarmType());
        hostAlarmConfig.setAlarmThreshold(command.getAlarmThreshold());
        hostAlarmConfig.setTriggerThreshold(command.getTriggerThreshold());
        hostAlarmConfig.setNotifySilence(command.getNotifySilence());
        hostAlarmConfig.init();
        hostAlarmConfigRepository.save(hostAlarmConfig);
        return PojoResult.succeed();
    }

    @Override
    public PojoResult<HostAlarmConfigWrapperDTO> getHostAlarmConfig(String hostId) {
        HostAlarmConfigWrapperDTO configWrapperDTO = new HostAlarmConfigWrapperDTO();

        List<HostAlarmConfig> hostAlarmConfigs = hostAlarmConfigRepository.find(new HostId(hostId));
        List<HostAlarmConfigDTO> hostAlarmConfigPOS = hostAlarmConfigDataAdapter.sourceToTarget(hostAlarmConfigs);
        configWrapperDTO.setAlarmConfigList(hostAlarmConfigPOS);

        List<HostAlarmGroup> hostAlarmGroups = hostAlarmGroupRepository.find(new HostId(hostId));
        List<HostAlarmGroupDTO> hostAlarmGroupDTOS = hostAlarmGroupDataAdapter.sourceToTarget(hostAlarmGroups);
        configWrapperDTO.setAlarmGroupList(hostAlarmGroupDTOS);
        return PojoResult.succeed(configWrapperDTO);
    }

    @Override
    public PojoResult<Boolean> setHostAlarmGroup(SetHostAlarmGroupCommand command) {
        ArrayList<HostAlarmGroup> alarmGroupList = new ArrayList<>();
        for (Long groupId : command.getGroupIdList()) {
            HostAlarmGroup alarmGroup = new HostAlarmGroup();
            alarmGroup.setAlarmGroupId(groupId);
            alarmGroup.setHostId(command.getHostId());
            alarmGroupList.add(alarmGroup);
        }
        hostAlarmGroupRepository.save(alarmGroupList);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public ListResult<HostAlarmConfigDTO> selectAlarmConfigByHostId(String hostId) {
        List<HostAlarmConfig> hostAlarmConfigs = hostAlarmConfigRepository.find(new HostId(hostId));
        List<HostAlarmConfigDTO> hostAlarmConfigDTOList = hostAlarmConfigDataAdapter.sourceToTarget(hostAlarmConfigs);
        return ListResult.succeed(hostAlarmConfigDTOList);
    }

    @Override
    public PojoResult<Boolean> deleteAlarmConfigByHostId(String hostId) {
        hostAlarmConfigRepository.deleteAlarmConfig(hostId);
        return PojoResult.succeed(Boolean.TRUE);
    }
}
