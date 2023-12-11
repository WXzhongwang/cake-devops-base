package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.ddd.repository.Repository;
import com.rany.cake.devops.base.domain.aggregate.TerminalSession;
import com.rany.cake.devops.base.domain.pk.TerminalSessionId;

import java.util.List;

/**
 * 终端
 *
 * @author zhongshengwang
 * @description 终端
 * @date 2023/1/28 21:01
 * @email 18668485565163.com
 */
public interface TerminalSessionRepository extends Repository<TerminalSession, TerminalSessionId> {

    int update(TerminalSession terminalSession);

    List<TerminalSession> selectAll();
}
