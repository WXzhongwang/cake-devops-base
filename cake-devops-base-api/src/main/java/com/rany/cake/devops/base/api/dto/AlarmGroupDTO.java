package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AlarmGroupDTO extends DTO {

    /**
     * 告警组ID
     */
    private Long id;

    /**
     * 告警组详情
     */
    private String groupName;

    /**
     * 描述
     */
    private String groupDescription;

    private List<AlarmGroupUserDTO> users;

    private List<AlarmGroupNotifyDTO> notifies;
    
}
