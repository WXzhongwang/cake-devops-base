package com.rany.cake.devops.base.domain.enums;

import lombok.Getter;

/**
 * SCM平台
 *
 * @author zhongshengwang
 * @description SCM平台
 * @date 2023/1/15 15:47
 * @email 18668485565163.com
 */
@Getter
public enum CodePlatformEnum {

    GITHUB(1, "GITHUB"),
    GITLAB(2, "GITLAB"),
    ;

    private final Integer code;

    private final String value;

    CodePlatformEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}
