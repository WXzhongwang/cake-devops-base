package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.entity.CommandExec;
import com.rany.cake.devops.base.domain.repository.param.CommandExecQueryParam;

public interface CommandExecRepository {
    CommandExec find(Long id);

    void remove(CommandExec commandExec);

    void save(CommandExec commandExec);

    int update(CommandExec commandExec);

    Page<CommandExec> page(CommandExecQueryParam queryParam);
}
