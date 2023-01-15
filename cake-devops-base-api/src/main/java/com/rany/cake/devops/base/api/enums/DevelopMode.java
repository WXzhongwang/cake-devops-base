package com.rany.cake.devops.base.api.enums;

import lombok.Getter;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/12 23:42
 * @email 18668485565163.com
 */
@Getter
public enum DevelopMode {

    /**
     * 分支开发模式
     */
    BRANCH(0, "BRANCH"),

    /**
     * gitflow模式
     */
    FLOW(1, "FLOW"),

    /**
     * 主干模式(自由模式)
     */
    FREEDOM(2, "FREEDOM");
    private Integer code;

    private String value;

    DevelopMode(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}
