package com.rany.cake.devops.base.service.handler.alarm;

import com.rany.cake.devops.base.api.dto.AppAccountDTO;
import com.rany.cake.devops.base.domain.entity.WebhookConfig;
import com.rany.cake.devops.base.util.enums.MachineAlarmType;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * 机器报警参数
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/29 18:43
 */
@Data
public class MachineAlarmContext {

    /**
     * 报警机器id
     */
    private String hostId;

    /**
     * 报警机器名称
     */
    private String hostName;

    /**
     * 报警主机
     */
    private String serverAddr;

    /**
     * 报警类型
     *
     * @see MachineAlarmType
     */
    private Integer alarmType;

    /**
     * 报警值
     */
    private Double alarmValue;

    /**
     * 报警时间
     */
    private Date alarmTime;

    /**
     * 用户映射
     */
    private Map<String, AppAccountDTO> userMapping;


    private WebhookConfig webhookConfig;

}
