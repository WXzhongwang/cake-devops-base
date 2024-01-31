package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemEnv extends BaseEntity<Long> {

    private String attrKey;
    private String attrValue;

    private Integer systemEnv;
    private String description;
}
