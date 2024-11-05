package com.rany.cake.devops.base.util.enums;

import lombok.Getter;

/**
 * 部署历史中的状态
 *
 * @author zhongshengwang
 * @description EnvDeployStatusEnum
 * @date 2022/12/28 20:15
 * @email 18668485565163.com
 */
@Getter
public enum DeployHistoryStatusEnum {

    PENDING("发布中", "0"),
    FAILED("已失败", "1"),
    SUCCESS("已成功", "2"),
    ;

    private final String code;
    private final String value;

    DeployHistoryStatusEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
