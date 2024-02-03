package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 报警组实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AlarmGroup extends BaseEntity<Long> {

    private String groupName;
    private String groupDescription;
}
