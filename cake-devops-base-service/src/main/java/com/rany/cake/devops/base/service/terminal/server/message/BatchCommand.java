package com.rany.cake.devops.base.service.terminal.server.message;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BatchCommand extends BaseServerMessage {
    private Boolean isBatch; // 会话批量指令
}