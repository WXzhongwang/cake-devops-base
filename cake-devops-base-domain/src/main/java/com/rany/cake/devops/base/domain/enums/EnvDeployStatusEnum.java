package com.rany.cake.devops.base.domain.enums;

import lombok.Getter;

/**
 * EnvDeployStatusEnum
 *
 * @author zhongshengwang
 * @description EnvDeployStatusEnum
 * @date 2022/12/28 20:15
 * @email 18668485565163.com
 */
@Getter
public enum EnvDeployStatusEnum {

    NORMAL("未发布", "0"),
    DEPLOYING("发布中", "1");

    private final String code;
    private final String value;

    EnvDeployStatusEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
