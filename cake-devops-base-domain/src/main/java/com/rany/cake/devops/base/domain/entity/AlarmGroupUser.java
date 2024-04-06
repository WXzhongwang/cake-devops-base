package com.rany.cake.devops.base.domain.entity;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseEntity;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
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


    public void init(String user) {
        this.creator = user;
        this.gmtCreate = DateUtil.date();
        this.gmtModified = DateUtil.date();
        this.isDeleted = DeleteStatusEnum.NO.getValue();
    }

    public Boolean delete(String user) {
        this.modifier = user;
        this.gmtModified = DateUtil.date();
        this.isDeleted = DeleteStatusEnum.YES.getValue();
        return Boolean.TRUE;
    }

    public Boolean modify(String user) {
        this.modifier = user;
        this.gmtModified = DateUtil.date();
        return Boolean.TRUE;
    }
}
