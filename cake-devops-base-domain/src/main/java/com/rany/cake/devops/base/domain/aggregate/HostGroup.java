package com.rany.cake.devops.base.domain.aggregate;

import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.cake.devops.base.domain.pk.HostGroupId;
import lombok.*;

import java.util.List;

/**
 * DBMS主机分组
 *
 * @author zhongshengwang
 * @description 主机管理
 * @date 2023/8/24 21:55
 * @email 18668485565163.com
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HostGroup extends BaseAggregateRoot implements IAggregate<HostGroupId> {
    private HostGroupId id;
    private String name;
    private Long parentId;
    private Integer sort;
    /**
     * 小组主机列表
     */
    private List<Host> hosts;
}

