package com.rany.cake.devops.base.api.command.agent;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SyncMonitorAgentCommand extends BaseCommand {

    private String hostId;
    private String url;
    private String accessToken;
}
