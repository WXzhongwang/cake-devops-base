package com.rany.cake.devops.base.api.command.monitor;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateMonitorAgentCommand extends BaseCommand {

    private Long id;
    private String accessToken;
    private String url;
}
