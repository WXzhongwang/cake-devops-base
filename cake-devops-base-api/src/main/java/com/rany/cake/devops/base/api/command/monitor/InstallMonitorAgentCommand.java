package com.rany.cake.devops.base.api.command.monitor;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class InstallMonitorAgentCommand extends BaseCommand {

    private String hostId;
    private Boolean upgrade;
}
