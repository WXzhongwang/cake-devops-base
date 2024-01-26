package com.rany.cake.devops.base.domain.aggregate;

import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.pk.ServerAccountId;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ServerAccount extends BaseAggregateRoot implements IAggregate<ServerAccountId> {

    private ServerAccountId serverAccountId;

    /**
     * 关联主机
     */
    private HostId hostId;

    private String authMode;

    private String username;

    private String displayName;

    /**
     * 0普通账户/1管理员
     */
    private Integer accountType;

    private String protocol;

    /**
     * 是否活跃可用
     */
    private Boolean active;
    /**
     * 凭据内容
     */
    private String credential;
    /**
     * publicKey
     */
    private String credential2;
    /**
     * 密码短语
     */
    private String passphrase;


    @Override
    public ServerAccountId getBizID() {
        return serverAccountId;
    }
}
