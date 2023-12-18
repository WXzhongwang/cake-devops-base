package com.rany.cake.devops.base.domain.aggregate;

import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.cake.devops.base.domain.pk.InstanceCommandId;
import com.rany.cake.devops.base.domain.pk.TerminalSessionInstanceId;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TerminalSessionInstanceCommand extends BaseAggregateRoot implements IAggregate<InstanceCommandId> {
    private InstanceCommandId id;
    private TerminalSessionInstanceId terminalSessionInstanceId;
    private String prompt;
    private Boolean isFormatted;
    private String input;
    private String inputFormatted;
    private String output;

    @Override
    public InstanceCommandId getBizID() {
        return id;
    }
}
