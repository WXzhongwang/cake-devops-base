package com.rany.cake.devops.base.infra.repository.impl;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.cake.devops.base.domain.entity.CommandExec;
import com.rany.cake.devops.base.domain.repository.CommandExecRepository;
import com.rany.cake.devops.base.domain.repository.param.CommandExecQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.infra.aop.PagingQuery;
import com.rany.cake.devops.base.infra.convertor.CommandExecDataConvertor;
import com.rany.cake.devops.base.infra.dao.CommandExecDao;
import com.rany.cake.devops.base.infra.mapper.CommandExecPOMapper;
import com.rany.cake.devops.base.infra.po.CommandExecPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 脚本模版
 *
 * @author zhongshenwang
 */
@AllArgsConstructor
@Service
public class CommandExecRepositoryImpl implements CommandExecRepository {
    private CommandExecPOMapper commandExecPOMapper;
    private CommandExecDao commandExecDao;
    private CommandExecDataConvertor commandExecDataConvertor;

    @Override
    public CommandExec find(Long id) {
        CommandExecPO commandExecPO = commandExecPOMapper.selectByPrimaryKey(id);
        return commandExecDataConvertor.targetToSource(commandExecPO);
    }

    @Override
    public void remove(CommandExec commandExec) {
        commandExecDao.update(commandExec);
    }

    @Override
    public void save(CommandExec commandExec) {
        commandExecDao.save(commandExec);
    }

    @Override
    public int update(CommandExec scriptTemplate) {
        return commandExecDao.update(scriptTemplate);
    }

    @Override
    @PagingQuery
    public Page<CommandExec> page(CommandExecQueryParam queryParam) {
        List<CommandExecPO> commandExecPOS = commandExecDao.queryCommandExec(queryParam);
        PageInfo<CommandExecPO> pageInfo = new PageInfo<>(commandExecPOS);
        List<CommandExec> configs = commandExecDataConvertor.targetToSource(commandExecPOS);
        return PageUtils.build(pageInfo, configs);
    }
}
