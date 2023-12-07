package com.rany.cake.devops.base.domain.enums;

import lombok.Getter;


@Getter
public enum InstanceSessionTypeEnum {

    /**
     * 实例会话类型
     */
    SERVER("SERVER"),
    // container
    CONTAINER_LOG("CONTAINER_LOG"),
    CONTAINER_TERMINAL("CONTAINER_TERMINAL");

    private final String type;

    InstanceSessionTypeEnum(String type) {
        this.type = type;
    }
}
