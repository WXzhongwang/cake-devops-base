package com.rany.cake.devops.base.web.request;

import com.rany.cake.devops.base.api.command.key.ModifyServerKeyCommand;
import lombok.Data;

@Data
public class UpdateServerKeyRequest {
    private ModifyServerKeyCommand command;
    private String fileBase64;
}
