package com.rany.cake.devops.base.api.command.terminal;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BatchDeleteTerminalLogCommand extends BaseCommand {

    private List<Long> ids;
}
