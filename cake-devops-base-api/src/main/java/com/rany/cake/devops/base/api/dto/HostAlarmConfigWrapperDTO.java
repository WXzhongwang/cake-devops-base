package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class HostAlarmConfigWrapperDTO extends DTO {
    private List<HostAlarmConfigDTO> alarmConfigList;
    private List<HostAlarmGroupDTO> alarmGroupList;
}
