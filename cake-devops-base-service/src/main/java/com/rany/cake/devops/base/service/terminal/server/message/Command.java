package com.rany.cake.devops.base.service.terminal.server.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Command extends BaseServerMessage {
    private String instanceId;
    private String data;
}