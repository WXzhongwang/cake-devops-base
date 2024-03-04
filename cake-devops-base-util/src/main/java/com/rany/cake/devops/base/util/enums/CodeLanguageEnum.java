package com.rany.cake.devops.base.util.enums;

import lombok.Getter;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/12 23:25
 * @email 18668485565163.com
 */
@Getter
public enum CodeLanguageEnum {

    JAVA(0, "JAVA"),
    PYTHON(1, "PYTHON"),
    GO(2, "GO");

    CodeLanguageEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    private final Integer code;

    private final String value;
}
