package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author zhongshengwang
 * @version 1.0
 * @date 2025/4/14 16:45
 * @slogon 找到银弹
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommandExecDTO extends DTO {
    private Long id;
    private String accountId;
    private String username;
    private String hostId;
    private Integer execType;
    private Integer execStatus;
    private Integer exitCode;
    private String execCommand;
    private String description;
    private String logPath;
    private Date startDate;
    private Date endDate;
}
