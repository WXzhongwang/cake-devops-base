package com.rany.cake.devops.base.service.app;

import com.rany.cake.devops.base.api.command.host.alarm.ConfigureAlarmCommand;
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
import com.rany.cake.devops.base.util.enums.MachineAlarmType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongshengwang
 */
@Service
@Slf4j
@AllArgsConstructor
public class HostAlarmConfigServiceImpl implements HostAlarmConfigService {

    private final HostAlarmConfigRepository hostAlarmConfigRepository;
    private final HostAlarmConfigDataAdapter hostAlarmConfigDataAdapter;
    private final HostAlarmGroupDataAdapter hostAlarmGroupDataAdapter;
    private final HostAlarmGroupRepository hostAlarmGroupRepository;

    @Override
    public Boolean setHostAlarmConfig(SetHostAlarmConfigCommand command) {
        hostAlarmConfigRepository.deleteAlarmConfig(command.getHostId(), command.getAlarmType());
        HostAlarmConfig hostAlarmConfig = new HostAlarmConfig();
        hostAlarmConfig.setHostId(command.getHostId());
        hostAlarmConfig.setAlarmType(command.getAlarmType());
        hostAlarmConfig.setAlarmThreshold(command.getAlarmThreshold());
        hostAlarmConfig.setTriggerThreshold(command.getTriggerThreshold());
        hostAlarmConfig.setNotifySilence(command.getNotifySilence());
        hostAlarmConfig.init(command.getUser());
        hostAlarmConfigRepository.save(hostAlarmConfig);
        return Boolean.TRUE;
    }

    @Override
    public Boolean setHostAlarmConfig(ConfigureAlarmCommand command) {
        HostId hostId = new HostId(command.getHostId());
        List<HostAlarmConfig> hostAlarmConfigs = hostAlarmConfigRepository.find(hostId);
        List<HostAlarmGroup> hostAlarmGroups = hostAlarmGroupRepository.find(hostId);

        if (CollectionUtils.isNotEmpty(hostAlarmConfigs)) {
            for (HostAlarmConfig hostAlarmConfig : hostAlarmConfigs) {
                hostAlarmConfig.delete(command.getUser());
                hostAlarmConfigRepository.update(hostAlarmConfig);
            }
        }
        if (CollectionUtils.isNotEmpty(hostAlarmGroups)) {
            for (HostAlarmGroup hostAlarmGroup : hostAlarmGroups) {
                hostAlarmGroup.delete(command.getUser());
                hostAlarmGroupRepository.update(hostAlarmGroup);
            }
        }

        SetHostAlarmConfigCommand cpu = command.getCpu();
        HostAlarmConfig cpuConfig = new HostAlarmConfig();
        cpuConfig.setHostId(command.getHostId());
        cpuConfig.setAlarmType(MachineAlarmType.CPU_USAGE.getType());
        cpuConfig.setAlarmThreshold(cpu.getAlarmThreshold());
        cpuConfig.setTriggerThreshold(cpu.getTriggerThreshold());
        cpuConfig.setNotifySilence(cpu.getNotifySilence());
        cpuConfig.init(command.getUser());
        hostAlarmConfigRepository.save(cpuConfig);

        SetHostAlarmConfigCommand memory = command.getCpu();
        HostAlarmConfig memoryConfig = new HostAlarmConfig();
        memoryConfig.setHostId(command.getHostId());
        memoryConfig.setAlarmType(MachineAlarmType.MEMORY_USAGE.getType());
        memoryConfig.setAlarmThreshold(memory.getAlarmThreshold());
        memoryConfig.setTriggerThreshold(memory.getTriggerThreshold());
        memoryConfig.setNotifySilence(memory.getNotifySilence());
        memoryConfig.init(command.getUser());
        hostAlarmConfigRepository.save(memoryConfig);

        ArrayList<HostAlarmGroup> alarmGroupList = new ArrayList<>();
        for (Long groupId : command.getGroupIdList()) {
            HostAlarmGroup alarmGroup = new HostAlarmGroup();
            alarmGroup.setAlarmGroupId(groupId);
            alarmGroup.setHostId(command.getHostId());
            alarmGroupList.add(alarmGroup);
            alarmGroup.init(command.getUser());
        }
        hostAlarmGroupRepository.save(alarmGroupList);
        return Boolean.TRUE;
    }

    @Override
    public HostAlarmConfigWrapperDTO getHostAlarmConfig(String hostId) {
        HostAlarmConfigWrapperDTO configWrapperDTO = new HostAlarmConfigWrapperDTO();

        List<HostAlarmConfig> hostAlarmConfigs = hostAlarmConfigRepository.find(new HostId(hostId));
        List<HostAlarmConfigDTO> hostAlarmConfigPOS = hostAlarmConfigDataAdapter.sourceToTarget(hostAlarmConfigs);
        configWrapperDTO.setAlarmConfigList(hostAlarmConfigPOS);

        List<HostAlarmGroup> hostAlarmGroups = hostAlarmGroupRepository.find(new HostId(hostId));
        List<HostAlarmGroupDTO> hostAlarmGroupDTOS = hostAlarmGroupDataAdapter.sourceToTarget(hostAlarmGroups);
        configWrapperDTO.setAlarmGroupList(hostAlarmGroupDTOS);
        return configWrapperDTO;
    }

    @Override
    public Boolean setHostAlarmGroup(SetHostAlarmGroupCommand command) {
        ArrayList<HostAlarmGroup> alarmGroupList = new ArrayList<>();
        for (Long groupId : command.getGroupIdList()) {
            HostAlarmGroup alarmGroup = new HostAlarmGroup();
            alarmGroup.setAlarmGroupId(groupId);
            alarmGroup.setHostId(command.getHostId());
            alarmGroupList.add(alarmGroup);
            alarmGroup.init(command.getUser());
        }
        hostAlarmGroupRepository.save(alarmGroupList);
        return Boolean.TRUE;
    }

    @Override
    public List<HostAlarmConfigDTO> selectAlarmConfigByHostId(String hostId) {
        List<HostAlarmConfig> hostAlarmConfigs = hostAlarmConfigRepository.find(new HostId(hostId));
        List<HostAlarmConfigDTO> hostAlarmConfigDTOList = hostAlarmConfigDataAdapter.sourceToTarget(hostAlarmConfigs);
        return hostAlarmConfigDTOList;
    }

    @Override
    public Boolean deleteAlarmConfigByHostId(String hostId) {
        hostAlarmConfigRepository.deleteAlarmConfig(hostId);
        return Boolean.TRUE;
    }
}
