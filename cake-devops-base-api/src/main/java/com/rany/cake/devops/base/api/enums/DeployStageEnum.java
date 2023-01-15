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
    PACKAGE(2, "打包阶段"),
    IMAGE(3, "镜像上传"),
    DEPLOY(4, "部署阶段"),
    NOTIFY(5, "通知阶段"),
    PUSH_MASTER(5, "合并主干");
    private Integer code;
    private String value;

    DeployStageEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}
