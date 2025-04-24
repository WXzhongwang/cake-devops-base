package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.exec.*;
import com.rany.cake.devops.base.api.common.enums.EventType;
import com.rany.cake.devops.base.api.dto.CommandExecDTO;
import com.rany.cake.devops.base.api.dto.CommandExecStatusDTO;
import com.rany.cake.devops.base.api.query.exec.CommandExecBasicQuery;
import com.rany.cake.devops.base.api.query.exec.CommandExecPageQuery;
import com.rany.cake.devops.base.api.service.CommandExecService;
import com.rany.cake.devops.base.service.aspect.annotation.EventLog;
import com.rany.cake.devops.base.util.Valid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 批量执行 api
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/4 17:44
 */
@Api(tags = "批量执行")
@RestController
@RequestMapping("/api/devops/batch-exec")
public class BatchExecCommandController {

    @Resource
    private CommandExecService commandExecService;

    @PostMapping("/submit")
    @ApiOperation(value = "提交批量执行任务")
    @EventLog(EventType.EXEC_SUBMIT)
    public ListResult<Long> submitTask(@RequestBody CreateCommandExecCommand request) {
        Valid.notBlank(request.getCommand());
        Valid.notEmpty(request.getHostIds());
        List<Long> commandExec = commandExecService.createCommandExec(request);
        return ListResult.succeed(commandExec);
    }

    @PostMapping("/page")
    @ApiOperation(value = "获取执行列表")
    public PageResult<CommandExecDTO> list(@RequestBody CommandExecPageQuery query) {
        Page<CommandExecDTO> commandExecDTOPage = commandExecService.pageCommandExec(query);
        return PageResult.succeed(commandExecDTOPage);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "获取执行详情")
    public PojoResult<CommandExecDTO> detail(@RequestBody CommandExecBasicQuery request) {
        Valid.notNull(request.getId());
        CommandExecDTO commandExec = commandExecService.getCommandExec(request);
        return PojoResult.succeed(commandExec);
    }

    @PostMapping("/write")
    @ApiOperation(value = "写入命令")
    public PojoResult<Boolean> write(@RequestBody WriteCommandExecCommand command) {
        Valid.notNull(command.getId());
        Valid.notEmpty(command.getCommand());
        commandExecService.writeCommand(command);
        return PojoResult.succeed();
    }

    @PostMapping("/terminate")
    @ApiOperation(value = "停止执行任务")
    @EventLog(EventType.EXEC_TERMINATE)
    public PojoResult<?> terminate(@RequestBody TerminateCommandExecCommand command) {
        Long id = Valid.notNull(command.getId());
        commandExecService.terminateExec(command);
        return PojoResult.succeed();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除任务")
    @EventLog(EventType.EXEC_DELETE)
    public PojoResult<Integer> delete(@RequestBody BatchDeleteCommandExecCommand command) {
        Valid.notEmpty(command.getIdList());
        return PojoResult.succeed(commandExecService.deleteCommands(command));
    }

    @PostMapping("/list-status")
    @ApiOperation(value = "获取状态列表")
    public ListResult<CommandExecStatusDTO> listStatus(@RequestBody BatchGetCommandExecCommand request) {
        Valid.notEmpty(request.getIdList());
        List<CommandExecStatusDTO> statusList = commandExecService.batchGetCommandExecStatus(request);
        return ListResult.succeed(statusList);
    }

}
