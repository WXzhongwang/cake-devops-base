package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.entity.CommandExec;
import com.rany.cake.devops.base.domain.repository.param.CommandExecQueryParam;

import java.util.List;

public interface CommandExecRepository {
    CommandExec find(Long id);

    List<CommandExec> findByIds(List<Long> ids);

    int deleteByIds(List<Long> ids, String modifier);

    void remove(CommandExec commandExec);

    void save(CommandExec commandExec);

    int update(CommandExec commandExec);

    List<CommandExec> list(CommandExecQueryParam queryParam);

    Page<CommandExec> page(CommandExecQueryParam queryParam);
}
