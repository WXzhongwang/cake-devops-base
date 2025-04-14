package com.rany.cake.devops.base.service.handler.exec;

import com.rany.cake.devops.base.api.service.WebSideMessageService;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.entity.CommandExec;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.CommandExecRepository;
import com.rany.cake.devops.base.domain.service.HostDomainService;
import com.rany.cake.devops.base.service.SpringHolder;
import com.rany.cake.devops.base.service.handler.host.HostConnectionService;
import com.rany.cake.devops.base.service.tail.TailSessionHolder;
import com.rany.cake.devops.base.service.utils.Utils;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.util.SchedulerPools;
import com.rany.cake.devops.base.util.StainCode;
import com.rany.cake.devops.base.util.enums.ExecStatus;
import com.rany.cake.devops.base.util.system.SystemEnvAttr;
import com.rany.cake.toolkit.lang.Threads;
import com.rany.cake.toolkit.lang.constant.Letters;
import com.rany.cake.toolkit.lang.exception.DisabledException;
import com.rany.cake.toolkit.lang.io.Files1;
import com.rany.cake.toolkit.lang.io.Streams;
import com.rany.cake.toolkit.lang.time.Dates;
import com.rany.cake.toolkit.lang.utils.Exceptions;
import com.rany.cake.toolkit.lang.utils.Strings;
import com.rany.cake.toolkit.net.remote.CommandExecutors;
import com.rany.cake.toolkit.net.remote.ExitCode;
import com.rany.cake.toolkit.net.remote.channel.CommandExecutor;
import com.rany.cake.toolkit.net.remote.channel.SessionStore;
import com.rany.ops.common.dto.account.AccountDTO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.OutputStream;
import java.util.Date;

/**
 * 命令执行器 基类
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2021/6/7 17:17
 */
@Slf4j
public class CommandExecHandler implements IExecHandler {

    private static final CommandExecRepository commandExecRepository = SpringHolder.getBean(CommandExecRepository.class);

    private static final HostConnectionService machineInfoService = SpringHolder.getBean(HostConnectionService.class);

    private static final HostDomainService hostDomainService = SpringHolder.getBean(HostDomainService.class);

    private static final ExecSessionHolder execSessionHolder = SpringHolder.getBean(ExecSessionHolder.class);

    private static final TailSessionHolder tailSessionHolder = SpringHolder.getBean(TailSessionHolder.class);

    private static final WebSideMessageService webSideMessageService = SpringHolder.getBean(WebSideMessageService.class);

    private final Long execId;

    private CommandExec record;

    private Host host;

    private SessionStore sessionStore;

    private CommandExecutor executor;

    private int exitCode;

    private String logPath;

    private OutputStream logOutputStream;

    private Date startTime, endTime;

    private final AccountDTO account;

    private volatile boolean terminated;

    protected CommandExecHandler(Long execId, AccountDTO account) {
        this.execId = execId;
        this.account = account;
    }

    @Override
    public void exec() {
        log.info("execHandler-提交 execId: {}", execId);
        Threads.start(this, SchedulerPools.EXEC_SCHEDULER);
    }

    @Override
    public void run() {
        log.info("execHandler-执行开始 execId: {}", execId);
        // 获取执行数据
        this.getExecData();
        // 检查状态
        if (record == null || !ExecStatus.WAITING.getStatus().equals(record.getExecStatus())) {
            return;
        }
        // 执行
        Exception ex = null;
        try {
            // 更新状态
            this.updateStatus(ExecStatus.RUNNABLE);
            execSessionHolder.addSession(execId, this);
            // 打开日志
            this.openLogger();
            // 打开executor
            this.sessionStore = machineInfoService.openSessionStore(host);
            this.executor = sessionStore.getCommandExecutor(Strings.replaceCRLF(record.getExecCommand()));
            // 执行命令
            CommandExecutors.syncExecCommand(executor, logOutputStream);
        } catch (Exception e) {
            ex = e;
        }
        // 回调
        try {
            if (terminated) {
                // 停止回调
                this.terminatedCallback();
            } else if (ex == null) {
                // 完成回调
                this.completeCallback();
            } else if (ex instanceof DisabledException) {
                // 机器未启用回调
                this.machineDisableCallback();
            } else {
                // 执行失败回调
                this.exceptionCallback(ex);
            }
        } finally {
            // 释放资源
            this.close();
        }
    }

    /**
     * 获取执行数据
     */
    private void getExecData() {
        this.record = commandExecRepository.find(execId);
        if (record == null) {
            return;
        }
        if (!ExecStatus.WAITING.getStatus().equals(record.getExecStatus())) {
            return;
        }
        // 查询机器信息
        this.host = hostDomainService.getHost(new HostId(record.getHostId()));
        // 设置日志信息
        File logFile = new File(Files1.getPath(SystemEnvAttr.LOG_PATH.getValue(), record.getLogPath()));
        Files1.touch(logFile);
        this.logPath = logFile.getAbsolutePath();
    }

    @Override
    public void write(String out) {
        executor.write(out);
    }

    @Override
    public void terminate() {
        log.info("execHandler-停止 execId: {}", execId);
        this.terminated = true;
        Streams.close(executor);
    }

    /**
     * 打开日志
     */
    @SneakyThrows
    private void openLogger() {
        // 打开日志流
        log.info("execHandler-打开日志流 {} {}", execId, logPath);
        File logFile = new File(logPath);
        this.logOutputStream = Files1.openOutputStreamFast(logFile);
        StringBuilder sb = new StringBuilder()
                .append(Utils.getStainKeyWords("# 准备执行命令", StainCode.GLOSS_GREEN))
                .append(Letters.LF)
                .append("@ssh:     ")
                .append(StainCode.prefix(StainCode.GLOSS_BLUE))
                .append(host.getUsername()).append("@")
                .append(host.getHostName()).append(":")
                .append(host.getPort())
                .append(StainCode.SUFFIX)
                .append(Letters.LF);
        sb.append("执行用户: ")
                .append(Utils.getStainKeyWords(account.getAccountName(), StainCode.GLOSS_BLUE))
                .append(Letters.LF);
        sb.append("执行任务: ")
                .append(Utils.getStainKeyWords(execId, StainCode.GLOSS_BLUE))
                .append(Letters.LF);
        sb.append("执行机器: ")
                .append(Utils.getStainKeyWords(host.getHostName(), StainCode.GLOSS_BLUE))
                .append(Letters.LF);
        sb.append("开始时间: ")
                .append(Utils.getStainKeyWords(Dates.format(startTime), StainCode.GLOSS_BLUE))
                .append(Letters.LF);
        String description = record.getDescription();
        if (!Strings.isBlank(description)) {
            sb.append("执行描述: ")
                    .append(Utils.getStainKeyWords(description, StainCode.GLOSS_BLUE))
                    .append(Letters.LF);
        }
        sb.append(Letters.LF)
                .append(Utils.getStainKeyWords("# 执行命令", StainCode.GLOSS_GREEN))
                .append(Letters.LF)
                .append(StainCode.prefix(StainCode.GLOSS_CYAN))
                .append(Utils.getEndLfWithEof(record.getExecCommand()))
                .append(StainCode.SUFFIX)
                .append(Utils.getStainKeyWords("# 开始执行", StainCode.GLOSS_GREEN))
                .append(Letters.LF);
        logOutputStream.write(Strings.bytes(sb.toString()));
        logOutputStream.flush();
    }

    /**
     * 停止回调
     */
    private void terminatedCallback() {
        log.info("execHandler-执行停止 execId: {}", execId);
        // 更新状态
        this.updateStatus(ExecStatus.TERMINATED);
        // 拼接日志
        StringBuilder log = new StringBuilder(Const.LF)
                .append(Utils.getStainKeyWords("# 命令执行停止", StainCode.GLOSS_YELLOW))
                .append(Letters.TAB)
                .append(Utils.getStainKeyWords(Dates.format(endTime), StainCode.GLOSS_BLUE))
                .append(Const.LF);
        this.appendLog(log.toString());
    }

    /**
     * 完成回调
     */
    private void completeCallback() {
        this.exitCode = executor.getExitCode();
        log.info("execHandler-执行完成 execId: {} exitCode: {}", execId, exitCode);
        // 更新状态
        this.updateStatus(ExecStatus.COMPLETE);
        // 拼接日志
        long used = endTime.getTime() - startTime.getTime();
        StringBuilder sb = new StringBuilder()
                .append(Letters.LF)
                .append(Utils.getStainKeyWords("# 命令执行完毕", StainCode.GLOSS_GREEN))
                .append(Letters.LF);
        sb.append("exitcode: ")
                .append(ExitCode.isSuccess(exitCode)
                        ? Utils.getStainKeyWords(exitCode, StainCode.GLOSS_BLUE)
                        : Utils.getStainKeyWords(exitCode, StainCode.GLOSS_RED))
                .append(Letters.LF);
        sb.append("结束时间: ")
                .append(Utils.getStainKeyWords(Dates.format(endTime), StainCode.GLOSS_BLUE))
                .append("  used ")
                .append(Utils.getStainKeyWords(Utils.interval(used), StainCode.GLOSS_BLUE))
                .append(" (")
                .append(StainCode.prefix(StainCode.GLOSS_BLUE))
                .append(used)
                .append("ms")
                .append(StainCode.SUFFIX)
                .append(")\n");
        this.appendLog(sb.toString());
        // 发送站内信
//        Map<String, Object> params = Maps.newMap();
//        params.put(EventKeys.ID, record.getId());
//        params.put(EventKeys.NAME, record.getMachineName());
//        webSideMessageService.saveWebSideMessage(MessageType.EXEC_SUCCESS, record.getId(),
//                record.getAccountId(),
//                record.getUserName(),
//                params);
    }

    /**
     * 机器未启用回调
     */
    private void machineDisableCallback() {
        log.info("execHandler-机器停用停止 execId: {}", execId);
        // 更新状态
        this.updateStatus(ExecStatus.TERMINATED);
        // 拼接日志
        StringBuilder log = new StringBuilder()
                .append(Const.LF)
                .append(Utils.getStainKeyWords("# 命令执行机器未启用", StainCode.GLOSS_YELLOW))
                .append(Letters.TAB)
                .append(Utils.getStainKeyWords(Dates.format(endTime), StainCode.GLOSS_BLUE))
                .append(Const.LF);
        this.appendLog(log.toString());
    }

    /**
     * 异常回调
     *
     * @param e e
     */
    private void exceptionCallback(Exception e) {
        log.error("execHandler-执行失败 execId: {}", execId, e);
        // 更新状态
        this.updateStatus(ExecStatus.EXCEPTION);
        // 拼接日志
        StringBuilder log = new StringBuilder()
                .append(Const.LF)
                .append(Utils.getStainKeyWords("# 命令执行异常", StainCode.GLOSS_RED))
                .append(Letters.TAB)
                .append(Utils.getStainKeyWords(Dates.format(endTime), StainCode.GLOSS_BLUE))
                .append(Letters.LF)
                .append(Exceptions.getStackTraceAsString(e))
                .append(Const.LF);
        this.appendLog(log.toString());
        // 发送站内信
//        Map<String, Object> params = Maps.newMap();
//        params.put(EventKeys.ID, record.getId());
//        params.put(EventKeys.NAME, record.getMachineName());
//        webSideMessageService.addMessage(MessageType.EXEC_FAILURE, record.getId(), record.getUserId(), record.getUserName(), params);
    }

    @SneakyThrows
    private void appendLog(String log) {
        logOutputStream.write(Strings.bytes(log));
        logOutputStream.flush();
    }

    /**
     * 更新状态
     *
     * @param status status
     */
    private void updateStatus(ExecStatus status) {
        Date now = new Date();
        record.setExecStatus(status.getStatus());
        record.setGmtModified(now);
        switch (status) {
            case RUNNABLE:
                this.startTime = now;
                record.setStartDate(now);
                break;
            case COMPLETE:
                this.endTime = now;
                record.setEndDate(now);
                record.setExitCode(exitCode);
                break;
            case EXCEPTION:
            case TERMINATED:
                this.endTime = now;
                record.setEndDate(now);
                break;
            default:
        }
        int effect = commandExecRepository.update(record);
        log.info("execHandler-更新状态 id: {}, status: {}, effect: {}", execId, status, effect);
    }

    @Override
    public void close() {
        log.info("execHandler-关闭 id: {}", execId);
        // 移除会话
        execSessionHolder.removeSession(execId);
        // 释放资源
        Streams.close(executor);
        Streams.close(sessionStore);
        Streams.close(logOutputStream);
        // 异步关闭正在tail的日志
        tailSessionHolder.asyncCloseTailFile(Const.HOST_MACHINE_ID, logPath);
    }

}
