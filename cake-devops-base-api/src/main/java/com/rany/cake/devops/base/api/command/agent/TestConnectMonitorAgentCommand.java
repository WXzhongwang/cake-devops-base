package com.rany.cake.devops.base.api.command.agent;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TestConnectMonitorAgentCommand extends BaseCommand {
    private String accessToken;
    private String url;
}
