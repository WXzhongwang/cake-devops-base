package com.rany.cake.devops.base.api.enums;

import lombok.Getter;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 16:04
 * @email 18668485565163.com
 */
@Getter
public enum AppRoleEnum {


    OWNER(1, "拥有者"),

    DEVELOPER(2, "开发"),

    TESTER(3, "测试"),

    OPERATOR(4, "运维"),

    ARCHITECT(5, "架构师"),

    REPORTER(6, "告警接收"),

    CHECKER(7, "部署审批");

    private Integer code;

    private String value;

    AppRoleEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}
