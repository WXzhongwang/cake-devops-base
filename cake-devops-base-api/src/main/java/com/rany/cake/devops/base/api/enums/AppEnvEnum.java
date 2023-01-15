package com.rany.cake.devops.base.api.enums;

import lombok.Getter;

/**
 * 应用开发环境
 *
 * @author zhongshengwang
 * @description 应用开发环境
 * @date 2023/1/15 15:47
 * @email 18668485565163.com
 */
@Getter
public enum AppEnvEnum {

    TEST(0, "测试"),

    PRE(1, "预发"),

    PROD(2, "线上");

    AppEnvEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    private Integer code;

    private String value;

}
