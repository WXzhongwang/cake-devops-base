package com.rany.cake.devops.base.api.enums;

import lombok.Getter;

/**
 * 部署文件类型
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/19 17:42
 * @email 18668485565163.com
 */
@Getter
public enum PackageFileTypeEnum {

    JAR(1, "jar"),
    WAR(2, "war"),
    ZIP(3, "zip"),
    ;

    private Integer code;

    private String value;

    private PackageFileTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static PackageFileTypeEnum getByCode(Integer code) {
        for (PackageFileTypeEnum item : PackageFileTypeEnum.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
