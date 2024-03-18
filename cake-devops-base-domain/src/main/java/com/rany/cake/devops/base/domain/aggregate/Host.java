package com.rany.cake.devops.base.domain.aggregate;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.cake.devops.base.domain.entity.GroupHost;
import com.rany.cake.devops.base.domain.entity.HostExtend;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.util.enums.CommonStatusEnum;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
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
    private HostId hostId;
    private String name;
    private String hostName;
    private String serverAddr;
    private Integer port;
    /**
     * 1. 基础账号密码认证
     * 2. 秘钥认证
     */
    private Integer authType;
    private String username;
    private String pwd;
    private String desc;
    private String verified;
    private String status;
    /**
     * 主机小组
     */
    private List<HostGroup> groups;
    /**
     * 拓展补充信息
     */
    private HostExtend hostExtend;

    /**
     * 代理ID
     */
    private Long proxyId;
    
    /**
     * 秘钥ID
     */
    private Long keyId;

    public Host(HostId hostId, String name, String hostName, String serverAddr, Integer port) {
        this.hostId = hostId;
        this.name = name;
        this.hostName = hostName;
        this.port = port;
        this.serverAddr = serverAddr;
        this.gmtCreate = DateUtil.date();
        this.gmtModified = this.gmtCreate;
        this.isDeleted = DeleteStatusEnum.NO.getValue();
        this.status = CommonStatusEnum.ENABLE.getValue();
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

    public Boolean copy(HostId hostId, List<GroupHost> groupHosts) {
        this.gmtCreate = DateUtil.date();
        this.gmtModified = DateUtil.date();
        this.hostId = hostId;
        for (GroupHost groupHost : groupHosts) {
            groupHost.setHostId(this.hostId.getHostId());
        }
        return Boolean.TRUE;
    }


    @Override
    public HostId getBizID() {
        return hostId;
    }
}

