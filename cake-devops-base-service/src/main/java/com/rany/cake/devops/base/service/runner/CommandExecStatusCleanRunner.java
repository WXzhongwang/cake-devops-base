package com.rany.cake.devops.base.service.runner;

import com.rany.cake.devops.base.domain.entity.CommandExec;
import com.rany.cake.devops.base.domain.repository.CommandExecRepository;
import com.rany.cake.devops.base.domain.repository.param.CommandExecQueryParam;
import com.rany.cake.devops.base.util.enums.ExecStatus;
import com.rany.cake.toolkit.lang.utils.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * zhongshengwang
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/5 0:18
 */
@Component
@Order(2200)
@Slf4j
public class CommandExecStatusCleanRunner implements CommandLineRunner {

    @Resource
    private CommandExecRepository commandExecRepository;

    @Override
    public void run(String... args) {
        log.info("重置命令执行状态-开始");
        CommandExecQueryParam queryParam = new CommandExecQueryParam();
        queryParam.setExecStatusList(Lists.of(ExecStatus.WAITING.getStatus(), ExecStatus.RUNNABLE.getStatus()));
        List<CommandExec> list = commandExecRepository.list(queryParam);
        list.forEach(commandExec -> {
            commandExec.setExecStatus(ExecStatus.TERMINATED.getStatus());
            commandExec.modify("system");
            commandExec.setGmtModified(new Date());
            commandExecRepository.update(commandExec);
        });
        log.info("重置命令执行状态-结束");
    }

}
