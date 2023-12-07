package com.rany.cake.devops.base.service.terminal.server.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DuplicateSession extends BaseServerMessage {
    // 源会话
    private Long sessionInstanceId;
    // token
    private String token;
}