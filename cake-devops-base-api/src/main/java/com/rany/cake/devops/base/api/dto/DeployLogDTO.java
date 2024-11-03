package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = true)
public class DeployLogDTO extends DTO {
    private String pipeKey;
    private String time;
    private String level;
    private String thread;
    private String location;
    private String message;
}
