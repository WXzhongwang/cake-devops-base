package com.rany.cake.devops.base.domain.entity;

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
}
