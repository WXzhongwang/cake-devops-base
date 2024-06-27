package com.rany.cake.devops.base.api.command.terminal;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class SaveTerminalLogCommand extends BaseCommand {

    private Long userId;

    private String username;

    private String hostId;

    private String hostName;

    private String hostTag;

    private String serverAddr;

    private String accessToken;

    private Date connectedTime;
    private String screenPath;
}
