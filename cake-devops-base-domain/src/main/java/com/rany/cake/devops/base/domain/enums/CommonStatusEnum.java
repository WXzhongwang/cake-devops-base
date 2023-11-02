package com.rany.cake.devops.base.domain.enums;

import lombok.Getter;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/28 20:15
 * @email 18668485565163.com
 */
@Getter
public enum CommonStatusEnum {

    ENABLE("启用", "0"),
    DISABLED("禁用", "1");

    private String code;
    private String value;

    CommonStatusEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
