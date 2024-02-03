package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 报警组用户实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AlarmGroupUser extends BaseEntity<Long> {


    private Long groupId;
    /**
     * 用户ID
     */
    private Long accountId;

    /**
     * 用户名称
     */
    private String username;
}
