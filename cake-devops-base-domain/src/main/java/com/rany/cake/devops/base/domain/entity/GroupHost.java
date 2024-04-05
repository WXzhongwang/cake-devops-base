package com.rany.cake.devops.base.domain.entity;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseEntity;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 分组和主机的关联关系
 * 多对对映射关系
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/8/24 22:03
 * @email 18668485565163.com
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class GroupHost extends BaseEntity<Long> {
    private String groupId;
    private String hostId;


    public GroupHost(String groupId, String hostId) {
        this.groupId = groupId;
        this.hostId = hostId;
        this.isDeleted = DeleteStatusEnum.NO.getValue();
    }

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
