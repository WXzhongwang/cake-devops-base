package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AlarmGroupUserDTO extends DTO {


    private Long id;
    

    private Long groupId;
    /**
     * 用户ID
     */
    private Long accountId;

    /**
     * 用户名称
     */
    private String username;
}
