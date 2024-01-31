package com.rany.cake.devops.base.domain.aggregate;


import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.cake.devops.base.domain.pk.ServerProxyId;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ServerProxy extends BaseAggregateRoot implements IAggregate<ServerProxyId> {

    private ServerProxyId serverProxyId;

    private String proxyHost;
    private String proxyPort;
    private String proxyUsername;
    private String proxyPassword;
    private Integer proxyType;
    private String description;

    @Override
    public ServerProxyId getBizID() {
        return serverProxyId;
    }
}
