package com.rany.cake.devops.plugin.common.http.vo;

import lombok.Data;

import java.util.List;

@Data
public class HostAlarmConfigWrapperDTO {
    private List<MachineAlarmConfig> alarmConfigList;
}
