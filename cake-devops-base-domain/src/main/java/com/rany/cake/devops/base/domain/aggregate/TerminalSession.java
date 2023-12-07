package com.rany.cake.devops.base.domain.aggregate;

import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.cake.devops.base.domain.enums.SessionTypeEnum;
import com.rany.cake.devops.base.domain.pk.TerminalSessionId;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TerminalSession extends BaseAggregateRoot implements IAggregate<TerminalSessionId> {
    private TerminalSessionId id;
    private String sessionId;
    private Long accountId;
    private String remoteAddr;
    private Boolean sessionClosed;
    private Date closeTime;
    private String serverHostname;
    private String serverAddr;
    private SessionTypeEnum sessionType;
}
