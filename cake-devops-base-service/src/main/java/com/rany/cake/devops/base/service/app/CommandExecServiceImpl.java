package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.exec.*;
import com.rany.cake.devops.base.api.dto.CommandExecDTO;
import com.rany.cake.devops.base.api.dto.CommandExecStatusDTO;
import com.rany.cake.devops.base.api.query.exec.CommandExecBasicQuery;
import com.rany.cake.devops.base.api.query.exec.CommandExecPageQuery;
import com.rany.cake.devops.base.api.service.CommandExecService;
import com.rany.cake.devops.base.api.service.HostEnvService;
import com.rany.cake.devops.base.api.service.SystemEnvService;
import com.rany.cake.devops.base.domain.entity.CommandExec;
import com.rany.cake.devops.base.domain.repository.CommandExecRepository;
import com.rany.cake.devops.base.domain.repository.param.CommandExecQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.CommandExecDataAdapter;
import com.rany.cake.devops.base.service.base.PathBuilders;
import com.rany.cake.devops.base.service.handler.exec.ExecSessionHolder;
import com.rany.cake.devops.base.service.handler.exec.IExecHandler;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.util.EnvConst;
import com.rany.cake.devops.base.util.MessageConst;
import com.rany.cake.devops.base.util.Valid;
import com.rany.cake.devops.base.util.enums.ExecStatus;
import com.rany.cake.devops.base.util.enums.ExecType;
import com.rany.cake.toolkit.lang.convert.Converts;
import com.rany.cake.toolkit.lang.utils.Collections;
import com.rany.cake.toolkit.lang.utils.Lists;
import com.rany.cake.toolkit.lang.utils.Strings;
import com.rany.ops.api.facade.account.AccountFacade;
import com.rany.ops.api.query.account.AccountBasicQuery;
import com.rany.ops.common.dto.account.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhongshengwang
 * @version 1.0
 * @date 2025/4/14 16:59
 * @slogon 找到银弹
 */
@Service
@Slf4j
@AllArgsConstructor
public class CommandExecServiceImpl implements CommandExecService {
    private final CommandExecRepository commandExecRepository;
    private final SystemEnvService systemEnvService;
    private final HostEnvService hostEnvService;
    private final AccountFacade accountFacade;
    private final CommandExecDataAdapter commandExecDataAdapter;
    private final ExecSessionHolder execSessionHolder;

    @Override
    public List<Long> createCommandExec(CreateCommandExecCommand command) {
        List<Long> commandExecIds = new ArrayList<>();
        AccountBasicQuery accountBasicQuery = new AccountBasicQuery();
        accountBasicQuery.setAccountId(Long.valueOf(command.getUser()));
        AccountDTO account = accountFacade.getAccount(accountBasicQuery);

        // 设置命令
        String commandContent = command.getCommand();
        final boolean containsEnv = commandContent.contains(EnvConst.SYMBOL);
        if (containsEnv) {
            // 查询系统环境变量
            Map<String, String> systemEnv = systemEnvService.getFullSystemEnv();
            commandContent = Strings.format(commandContent, EnvConst.SYMBOL, systemEnv);
        }
        for (String hostId : command.getHostIds()) {
            CommandExec commandExec = new CommandExec();
            commandExec.setAccountId(command.getUser());
            commandExec.setHostId(hostId);
            commandExec.setUsername(account.getAccountName());
            commandExec.setExecType(ExecType.BATCH_EXEC.getType());
            if (containsEnv) {
                // 查询机器环境变量
                Map<String, String> machineEnv = hostEnvService.getMachineEnv(hostId);
                commandExec.setExecCommand(Strings.format(commandContent, EnvConst.SYMBOL, machineEnv));
            } else {
                commandExec.setExecCommand(commandContent);
            }

            commandExec.setExecCommand(commandContent);
            commandExec.setDescription(command.getDescription());
            commandExec.init(command.getUser());
            commandExecRepository.save(commandExec);

            // 设置日志路径
            Long execId = commandExec.getId();
            String logPath = PathBuilders.getExecLogPath(Const.COMMAND_DIR, execId, hostId);
            commandExec.setLogPath(logPath);
            commandExecRepository.update(commandExec);
            // 提交执行任务
            IExecHandler.with(execId, account).exec();
            commandExecIds.add(execId);
        }
        return commandExecIds;
    }

    @Override
    public Boolean deleteCommandExec(DeleteCommandExecCommand command) {
        CommandExec commandExec = commandExecRepository.find(command.getId());
        commandExec.delete(command.getUser());
        commandExecRepository.remove(commandExec);
        return Boolean.TRUE;
    }

    @Override
    public void writeCommand(WriteCommandExecCommand command) {
        CommandExec execDO = commandExecRepository.find(command.getId());
        Valid.notNull(execDO, MessageConst.EXEC_TASK_ABSENT);
        Valid.isTrue(ExecStatus.RUNNABLE.getStatus().equals(execDO.getExecStatus()), MessageConst.ILLEGAL_STATUS);
        // 获取任务信息
        IExecHandler session = execSessionHolder.getSession(command.getId());
        Valid.notNull(session, MessageConst.EXEC_TASK_THREAD_ABSENT);
        session.write(command.getCommand());
    }

    @Override
    public void terminateExec(TerminateCommandExecCommand terminateCommandExecCommand) {
        CommandExec execDO = commandExecRepository.find(terminateCommandExecCommand.getId());
        Valid.notNull(execDO, MessageConst.EXEC_TASK_ABSENT);
        Valid.isTrue(ExecStatus.RUNNABLE.getStatus().equals(execDO.getExecStatus()), MessageConst.ILLEGAL_STATUS);
        // 获取任务并停止
        IExecHandler session = execSessionHolder.getSession(terminateCommandExecCommand.getId());
        Valid.notNull(session, MessageConst.SESSION_PRESENT);
        session.terminate();
        // 设置日志参数
        // EventParamsHolder.addParam(EventKeys.ID, id);
    }


    @Override
    public Integer deleteCommands(BatchDeleteCommandExecCommand commands) {
        List<CommandExec> execList = commandExecRepository.findByIds(commands.getIdList());
        Valid.notEmpty(execList, MessageConst.EXEC_TASK_ABSENT);
        // 检查是否可删除
        boolean canDelete = execList.stream()
                .map(CommandExec::getExecStatus)
                .noneMatch(s -> ExecStatus.WAITING.getStatus().equals(s) || ExecStatus.RUNNABLE.getStatus().equals(s));
        Valid.isTrue(canDelete, MessageConst.ILLEGAL_STATUS);
        return commandExecRepository.deleteByIds(commands.getIdList(), commands.getUser());
    }


    @Override
    public CommandExecDTO getCommandExec(CommandExecBasicQuery basicQuery) {
        CommandExec commandExec = commandExecRepository.find(basicQuery.getId());
        return commandExecDataAdapter.sourceToTarget(commandExec);
    }

    @Override
    public List<CommandExecStatusDTO> batchGetCommandExecStatus(BatchGetCommandExecCommand batchGetCommandExecCommand) {
        List<CommandExec> commandExecs = commandExecRepository.findByIds(batchGetCommandExecCommand.getIdList());
        if (Collections.isEmpty(commandExecs)) {
            return Lists.empty();
        }
        return commandExecs.stream().map(s ->
                        Converts.to(s, CommandExecStatusDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<CommandExecDTO> pageCommandExec(CommandExecPageQuery pageQuery) {
        CommandExecQueryParam commandExecQueryParam = commandExecDataAdapter.convertParam(pageQuery);
        Page<CommandExec> commandExecPage = commandExecRepository.page(commandExecQueryParam);
        Collection<CommandExec> commandExecs = commandExecPage.getItems();
        List<CommandExecDTO> commandExecDTOS = commandExecDataAdapter.sourceToTarget(new ArrayList<>(commandExecs));
        return PageUtils.build(commandExecPage, commandExecDTOS);
    }
}
