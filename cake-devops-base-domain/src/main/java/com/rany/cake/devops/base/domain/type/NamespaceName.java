package com.rany.cake.devops.base.domain.type;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 命名空间名称
 *
 * @author zhongshengwang
 * @description 命名空间名称
 * @date 2023/1/15 16:23
 * @email 18668485565163.com
 */
@Data
@NoArgsConstructor
public class NamespaceName {
    String name;

    public NamespaceName(String name) {
        if (name == null || name.length() < 8) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }
}
