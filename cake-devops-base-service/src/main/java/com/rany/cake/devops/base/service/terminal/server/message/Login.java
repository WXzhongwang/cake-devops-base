package com.rany.cake.devops.base.service.terminal.server.message;

import com.rany.cake.devops.base.service.terminal.ILoginMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class Login extends BaseServerMessage implements ILoginMessage {
    /**
     * 主机ID集合
     */
    private Set<Long> serverNodes;
    private String token;
}