package com.rany.cake.devops.base.web.request;

import com.rany.cake.devops.base.api.command.key.CreateServerKeyCommand;
import lombok.Data;

@Data
public class CreateServerKeyRequest {
    private CreateServerKeyCommand command;
    private String fileBase64;
}
