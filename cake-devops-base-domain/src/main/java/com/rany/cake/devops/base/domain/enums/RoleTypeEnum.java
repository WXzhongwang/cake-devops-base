package com.rany.cake.devops.base.domain.enums;

import lombok.Getter;

/**
 * 角色类型
 *
 * @author zhongshengwang
 * @description 应用开发环境
 * @date 2023/1/15 15:47
 * @email 18668485565163.com
 */
@Getter
public enum RoleTypeEnum {

    NORMAL(0, "普通用户"), ADMIN(1, "管理员");

    private Integer code;

    private String value;

    RoleTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static RoleTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (RoleTypeEnum item : RoleTypeEnum.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
