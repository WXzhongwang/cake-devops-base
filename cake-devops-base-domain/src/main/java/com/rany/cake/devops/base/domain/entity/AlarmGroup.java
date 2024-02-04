package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 报警组实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AlarmGroup extends BaseEntity<Long> {

    private String groupName;
    private String groupDescription;
    private List<AlarmGroupUser> users;
    private List<AlarmGroupNotify> notifies;
}
