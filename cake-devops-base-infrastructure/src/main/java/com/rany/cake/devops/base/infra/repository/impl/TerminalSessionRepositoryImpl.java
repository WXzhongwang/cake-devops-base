package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.aggregate.TerminalSession;
import com.rany.cake.devops.base.domain.pk.TerminalSessionId;
import com.rany.cake.devops.base.domain.repository.TerminalSessionRepository;
import com.rany.cake.devops.base.infra.convertor.TerminalSessionDataConvertor;
import com.rany.cake.devops.base.infra.dao.TerminalSessionDao;
import com.rany.cake.devops.base.infra.mapper.TerminalSessionPOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@AllArgsConstructor
public class TerminalSessionRepositoryImpl implements TerminalSessionRepository {
    private final TerminalSessionPOMapper terminalSessionPOMapper;
    private final TerminalSessionDataConvertor terminalSessionDataConvertor;
    private final TerminalSessionDao terminalSessionDao;

    @Override
    public int update(TerminalSession terminalSession) {
        return 0;
    }

    @Override
    public List<TerminalSession> selectAll() {
        return null;
    }

    @Override
    public TerminalSession find(@NotNull TerminalSessionId terminalSessionId) {
        return null;
    }

    @Override
    public void remove(@NotNull TerminalSession terminalSession) {

    }

    @Override
    public void save(@NotNull TerminalSession terminalSession) {

    }
}
