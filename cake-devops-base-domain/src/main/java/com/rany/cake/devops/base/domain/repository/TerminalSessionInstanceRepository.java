package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.ddd.repository.Repository;
import com.rany.cake.devops.base.domain.aggregate.TerminalSessionInstance;
import com.rany.cake.devops.base.domain.pk.TerminalSessionInstanceId;

import java.util.List;

/**
 * 终端会话实例
 *
 * @author zhongshengwang
 * @description 终端会话实例
 * @date 2023/1/28 21:01
 * @email 18668485565163.com
 */
public interface TerminalSessionInstanceRepository extends Repository<TerminalSessionInstance, TerminalSessionInstanceId> {

    int update(TerminalSessionInstance terminalSessionInstance);

    List<TerminalSessionInstance> selectBySessionId(String sessionId);
}
