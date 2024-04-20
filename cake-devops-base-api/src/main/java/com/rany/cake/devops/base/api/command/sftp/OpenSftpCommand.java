package com.rany.cake.devops.base.api.command.sftp;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OpenSftpCommand extends BaseCommand {

    private String hostId;
}
