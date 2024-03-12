package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class HostAlarmConfigDTO extends DTO {
    private String hostId;
    private Integer alarmType;
    private Double alarmThreshold;
    private Integer triggerThreshold;
    private Integer notifySilence;
}
