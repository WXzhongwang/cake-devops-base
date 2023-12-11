package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.aggregate.TerminalSessionInstanceCommand;
import com.rany.cake.devops.base.domain.pk.InstanceCommandId;
import com.rany.cake.devops.base.domain.repository.TerminalSessionInstanceCommandRepository;
import com.rany.cake.devops.base.infra.convertor.TerminalSessionInstanceCommandDataConvertor;
import com.rany.cake.devops.base.infra.mapper.TerminalSessionInstanceCommandPOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@AllArgsConstructor
public class TerminalSessionInstanceCommandRepositoryImpl implements TerminalSessionInstanceCommandRepository {
    private final TerminalSessionInstanceCommandPOMapper terminalSessionInstanceCommandPOMapper;
    private final TerminalSessionInstanceCommandDataConvertor terminalSessionInstanceCommandDataConvertor;


    @Override
    public int update(TerminalSessionInstanceCommand terminalSessionInstanceCommand) {
        return 0;
    }

    @Override
    public TerminalSessionInstanceCommand find(@NotNull InstanceCommandId instanceCommandId) {
        return null;
    }

    @Override
    public void remove(@NotNull TerminalSessionInstanceCommand terminalSessionInstanceCommand) {

    }

    @Override
    public void save(@NotNull TerminalSessionInstanceCommand terminalSessionInstanceCommand) {

    }
}
