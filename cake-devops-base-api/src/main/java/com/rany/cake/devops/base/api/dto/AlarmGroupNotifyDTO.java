package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AlarmGroupNotifyDTO extends DTO {

    private Long id;

    private Long notifyId;
    
    private Integer notifyType;
}
