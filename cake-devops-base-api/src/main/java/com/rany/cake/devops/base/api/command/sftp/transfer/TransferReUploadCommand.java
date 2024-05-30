package com.rany.cake.devops.base.api.command.sftp.transfer;


import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransferReUploadCommand extends BaseCommand {
    private String fileToken;
    private String sessionToken;
}
