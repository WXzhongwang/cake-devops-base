package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.exec.CreateCommandExecCommand;
import com.rany.cake.devops.base.api.command.exec.DeleteCommandExecCommand;
import com.rany.cake.devops.base.api.dto.CommandExecDTO;
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
import com.rany.cake.devops.base.service.handler.exec.IExecHandler;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.util.EnvConst;
import com.rany.cake.devops.base.util.enums.ExecType;
import com.rany.cake.toolkit.lang.utils.Strings;
import com.rany.ops.api.facade.account.AccountFacade;
import com.rany.ops.api.query.account.AccountBasicQuery;
import com.rany.ops.common.dto.account.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
    public CommandExecDTO getCommandExec(CommandExecBasicQuery basicQuery) {
        CommandExec commandExec = commandExecRepository.find(basicQuery.getId());
        return commandExecDataAdapter.sourceToTarget(commandExec);
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
