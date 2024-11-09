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
public enum DeployContentEnum {

    NEW("新增发布"),
    ;

    private final String content;

    DeployContentEnum(String content) {
        this.content = content;
    }
}
