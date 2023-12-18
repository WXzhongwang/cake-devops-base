package com.rany.cake.devops.base.domain.aggregate;

import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.cake.devops.base.domain.pk.TerminalSessionId;
import com.rany.cake.devops.base.domain.pk.TerminalSessionInstanceId;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TerminalSessionInstance extends BaseAggregateRoot implements IAggregate<TerminalSessionInstanceId> {
    private TerminalSessionInstanceId id;
    private TerminalSessionId sessionId;
    private Long instanceId;
    private Long duplicateInstanceId;
    private String instanceSessionType;
    private String loginUser;
    private String hostIp;
    private Long outputSize;
    private String instanceClosed;
    private Date closeTime;

    @Override
    public TerminalSessionInstanceId getBizID() {
        return id;
    }
}
