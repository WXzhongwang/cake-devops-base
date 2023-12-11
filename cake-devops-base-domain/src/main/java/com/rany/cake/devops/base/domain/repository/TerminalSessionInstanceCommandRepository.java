package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.ddd.repository.Repository;
import com.rany.cake.devops.base.domain.aggregate.TerminalSessionInstanceCommand;
import com.rany.cake.devops.base.domain.pk.InstanceCommandId;

/**
 * 终端指令
 *
 * @author zhongshengwang
 * @description 终端指令
 * @date 2023/1/28 21:01
 * @email 18668485565163.com
 */
public interface TerminalSessionInstanceCommandRepository extends Repository<TerminalSessionInstanceCommand, InstanceCommandId> {

    int update(TerminalSessionInstanceCommand terminalSessionInstanceCommand);
}
