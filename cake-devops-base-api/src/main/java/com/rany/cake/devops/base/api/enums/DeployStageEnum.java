package com.rany.cake.devops.base.api.enums;

import lombok.Getter;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 21:19
 * @email 18668485565163.com
 */
@Getter
public enum DeployStageEnum {
    TEST(0, "测试阶段"),
    COMPILE(1, "编译阶段"),
    BUILD(2, "构建阶段"),
    DEPLOY(3, "发布阶段"),
    NOTIFY(4, "通知阶段"),
    PUSH_MASTER(5, "合并主干");
    private Integer code;
    private String value;

    DeployStageEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}
