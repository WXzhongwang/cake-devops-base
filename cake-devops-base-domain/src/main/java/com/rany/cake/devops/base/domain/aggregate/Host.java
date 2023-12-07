package com.rany.cake.devops.base.domain.aggregate;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.cake.devops.base.domain.entity.HostExtend;
import com.rany.cake.devops.base.domain.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.domain.pk.HostId;
import lombok.*;

import java.util.List;

/**
 * DBMS主机管理
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
public class Host extends BaseAggregateRoot implements IAggregate<HostId> {
    private HostId id;
    private String name;
    private String hostName;
    private String serverAddr;
    private Integer port;
    private String username;
    private String pkey;
    private String desc;
    private String verified;
    /**
     * 主机小组
     */
    private List<HostGroup> groups;
    /**
     * 拓展补充信息
     */
    private HostExtend hostExtend;

    public Host(HostId id, String name, String hostName, Integer port) {
        this.id = id;
        this.name = name;
        this.hostName = hostName;
        this.port = port;
    }

    public Boolean delete() {
        this.gmtModified = DateUtil.date();
        this.isDeleted = DeleteStatusEnum.YES.getValue();
        return Boolean.TRUE;
    }

    public Boolean modify() {
        this.gmtModified = DateUtil.date();
        return Boolean.TRUE;
    }
}

