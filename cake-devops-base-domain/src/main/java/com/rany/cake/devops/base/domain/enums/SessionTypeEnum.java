package com.rany.cake.devops.base.domain.enums;

import lombok.Getter;


@Getter
public enum SessionTypeEnum {

    /**
     * 会话类型
     */
    WEB_TERMINAL("WEB_TERMINAL"),
    SSH_SERVER("SSH_SERVER"),
    KUBERNETES_TERMINAL("KUBERNETES_TERMINAL");

    private final String type;

    SessionTypeEnum(String type) {
        this.type = type;
    }

}
