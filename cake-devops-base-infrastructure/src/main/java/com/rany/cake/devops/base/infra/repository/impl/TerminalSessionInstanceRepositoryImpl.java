package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.aggregate.TerminalSessionInstance;
import com.rany.cake.devops.base.domain.pk.TerminalSessionInstanceId;
import com.rany.cake.devops.base.domain.repository.TerminalSessionInstanceRepository;
import com.rany.cake.devops.base.infra.convertor.TerminalSessionInstanceDataConvertor;
import com.rany.cake.devops.base.infra.dao.TerminalSessionInstanceDao;
import com.rany.cake.devops.base.infra.mapper.TerminalSessionInstancePOMapper;
import com.rany.cake.devops.base.infra.po.TerminalSessionInstancePO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@AllArgsConstructor
public class TerminalSessionInstanceRepositoryImpl implements TerminalSessionInstanceRepository {
    private final TerminalSessionInstancePOMapper terminalSessionInstancePOMapper;
    private final TerminalSessionInstanceDataConvertor terminalSessionInstanceDataConvertor;
    private final TerminalSessionInstanceDao terminalSessionInstanceDao;

    @Override
    public int update(TerminalSessionInstance terminalSessionInstance) {
        return terminalSessionInstanceDao.update(terminalSessionInstance);
    }

    @Override
    public List<TerminalSessionInstance> selectBySessionId(String sessionId) {
        List<TerminalSessionInstancePO> terminalSessionInstancePOS = terminalSessionInstanceDao.selectBySessionId(sessionId);
        return terminalSessionInstanceDataConvertor.targetToSource(terminalSessionInstancePOS);
    }

    @Override
    public TerminalSessionInstance find(@NotNull TerminalSessionInstanceId terminalSessionInstanceId) {
        TerminalSessionInstancePO terminalSessionInstancePO = terminalSessionInstancePOMapper.selectByPrimaryKey(terminalSessionInstanceId.getId());
        return terminalSessionInstanceDataConvertor.targetToSource(terminalSessionInstancePO);
    }

    @Override
    public void remove(@NotNull TerminalSessionInstance terminalSessionInstance) {
        terminalSessionInstanceDao.update(terminalSessionInstance);
    }

    @Override
    public void save(@NotNull TerminalSessionInstance terminalSessionInstance) {
        terminalSessionInstanceDao.save(terminalSessionInstance);
    }
}
