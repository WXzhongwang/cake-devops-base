package com.rany.cake.devops.base.api.command.host;


import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ping主机
 *
 * @author zhongshengwang
 * @description ping主机
 * @date 2022/12/27 20:40
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PingHostCommand extends BaseCommand {

    /**
     * hostId
     */
    private String hostId;
}
