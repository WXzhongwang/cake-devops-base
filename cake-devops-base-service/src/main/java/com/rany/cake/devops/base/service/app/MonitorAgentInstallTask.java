package com.rany.cake.devops.base.service.app;

import com.rany.cake.devops.base.api.dto.AppAccountDTO;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.base.MonitorConst;
import com.rany.cake.devops.base.domain.entity.HostMonitor;
import com.rany.cake.devops.base.domain.entity.WebSideMessage;
import com.rany.cake.devops.base.domain.repository.HostMonitorRepository;
import com.rany.cake.devops.base.domain.repository.WebSideMessageRepository;
import com.rany.cake.devops.base.domain.service.HostDomainService;
import com.rany.cake.devops.base.service.base.PathBuilders;
import com.rany.cake.devops.base.service.handler.agent.MonitorAgents;
import com.rany.cake.devops.base.service.handler.host.HostConnectionService;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.util.EventKeys;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.util.enums.MonitorStatus;
import com.rany.cake.devops.base.util.enums.message.MessageType;
import com.rany.cake.devops.base.util.enums.message.ReadStatus;
import com.rany.cake.devops.base.util.system.SystemEnvAttr;
import com.rany.cake.toolkit.lang.Threads;
import com.rany.cake.toolkit.lang.constant.Letters;
import com.rany.cake.toolkit.lang.convert.Converts;
import com.rany.cake.toolkit.lang.io.Files1;
import com.rany.cake.toolkit.lang.io.Streams;
import com.rany.cake.toolkit.lang.time.Dates;
import com.rany.cake.toolkit.lang.utils.Arrays1;
import com.rany.cake.toolkit.lang.utils.Exceptions;
import com.rany.cake.toolkit.lang.utils.Maps;
import com.rany.cake.toolkit.lang.utils.Strings;
import com.rany.cake.toolkit.net.remote.CommandExecutors;
import com.rany.cake.toolkit.net.remote.ExitCode;
import com.rany.cake.toolkit.net.remote.channel.CommandExecutor;
import com.rany.cake.toolkit.net.remote.channel.SessionStore;
import com.rany.cake.toolkit.net.remote.channel.SftpExecutor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.OutputStream;
import java.util.Map;

/**
 * 监控插件安装任务
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/12 15:35
 */
@Slf4j
public class MonitorAgentInstallTask implements Runnable {
    private HostConnectionService hostConnectionService;
    private HostDomainService hostDomainService;
    private MonitorAgents monitorAgents;
    private HostMonitorRepository hostMonitorRepository;
    private WebSideMessageRepository webSideMessageService;
    private AppAccountDTO appAccountDTO;
    private Host host;

    private SessionStore session;
    private OutputStream logStream;

    public MonitorAgentInstallTask(HostConnectionService hostConnectionService,
                                   HostDomainService hostDomainService,
                                   MonitorAgents monitorAgents,
                                   HostMonitorRepository hostMonitorRepository,
                                   WebSideMessageRepository webSideMessageService,
                                   AppAccountDTO appAccountDTO,
                                   Host host) {
        this.hostConnectionService = hostConnectionService;
        this.hostDomainService = hostDomainService;
        this.monitorAgents = monitorAgents;
        this.hostMonitorRepository = hostMonitorRepository;
        this.webSideMessageService = webSideMessageService;
        this.appAccountDTO = appAccountDTO;
        this.host = host;
    }

    @Override
    public void run() {
        String hostId = host.getHostId().getHostId();
        log.info("开始安装监控插件 machineId: {}", host.getHostId().getHostId());
        try {
            // 查询机器信息
            // 打开日志流
            String logPath = PathBuilders.getInstallLogPath(host.getHostId().getHostId(), MonitorConst.AGENT_FILE_NAME_PREFIX);
            File logFile = new File(Files1.getPath(SystemEnvAttr.LOG_PATH.getValue(), logPath));
            Files1.touch(logFile);
            this.logStream = Files1.openOutputStreamFast(logFile);
            // 打开会话
            this.session = hostConnectionService.openSessionStore(host);
            String pluginDirectory = PathBuilders.getPluginPath(host.getUsername());
            String startScriptPath = pluginDirectory + "/" + MonitorConst.START_SCRIPT_FILE_NAME;
            // 传输
            this.transferAgentFile(hostId, pluginDirectory, startScriptPath);
            // 启动
            this.startAgentApp(startScriptPath);
            // 同步等待
            this.checkAgentRunStatus(hostId);
            // 拼接日志
            this.appendLog("安装成功 {}", Dates.current());
        } catch (Exception e) {
            // 拼接日志
            this.appendLog("安装失败 {}", Exceptions.getStackTraceAsString(e));
            // 查询配置
            HostMonitor monitor = hostMonitorRepository.findByHostId(hostId);

            // 更新状态
            monitor.setMonitorStatus(MonitorStatus.NOT_START.getStatus());
            hostMonitorRepository.update(monitor);
            // 发送站内信
            this.sendWebSideMessage(MessageType.MACHINE_AGENT_INSTALL_FAILURE, host, appAccountDTO);
        } finally {
            Streams.close(session);
            Streams.close(logStream);
        }
    }

    /**
     * 传输文件
     *
     * @param pluginDirectory pluginDirectory
     * @param startScriptPath startScriptPath
     */
    private void transferAgentFile(String hostId, String pluginDirectory, String startScriptPath) {
        // 传输脚本目录
        String agentPath = pluginDirectory + Const.LIB_DIR + "/" + MonitorConst.getAgentFileName();
        SftpExecutor executor = null;
        try {
            // 打开 sftp 连接
            String charset = hostDomainService.getSftpCharset(hostId);
            executor = session.getSftpExecutor(charset);
            executor.connect();
            // 传输启动脚本文件
            String startScript = this.getStartScript(hostId, agentPath);
            this.appendLog("开始生成启动脚本 path: {}, command: \n{}", agentPath, startScript);
            executor.write(startScriptPath, Strings.bytes(startScript));
            executor.chmod(startScriptPath, 777);
            // 传输 agent 文件
            File localAgentFile = new File(SystemEnvAttr.MACHINE_MONITOR_AGENT_PATH.getValue());
            // 查询文件是否存在
            long size = executor.getSize(agentPath);
            long totalSize = localAgentFile.length();
            if (totalSize != size) {
                // 传输文件
                this.appendLog("插件包不存在-开始传输 {} {}B", agentPath, totalSize);
                executor.uploadFile(agentPath, localAgentFile);
                this.appendLog("插件包传输完成 {}", agentPath);
            } else {
                this.appendLog("插件包已存在 {}", agentPath);
            }
        } catch (Exception e) {
            throw Exceptions.sftp("文件上传失败", e);
        } finally {
            Streams.close(executor);
        }
    }

    /**
     * 启动 agent 应用
     *
     * @param startScriptPath startScriptPath
     */
    private void startAgentApp(String startScriptPath) {
        CommandExecutor executor = null;
        try {
            // 执行启动命令
            this.appendLog("开始执行启动脚本 path: {}", startScriptPath);
            executor = session.getCommandExecutor("bash -l " + startScriptPath);
            executor.getChannel().setPty(false);
            CommandExecutors.syncExecCommand(executor, logStream);
            int exitCode = executor.getExitCode();
            if (!ExitCode.isSuccess(exitCode)) {
                throw Exceptions.runtime("执行启动失败");
            }
            this.appendLog("命令执行完成 exit: {}", exitCode);
        } catch (Exception e) {
            throw Exceptions.runtime("执行启动异常", e);
        } finally {
            Streams.close(executor);
        }
    }

    /**
     * 同步检查 agent 状态
     */
    private void checkAgentRunStatus(String hostId) {
        // 查询配置
        HostMonitor monitor = hostMonitorRepository.findByHostId(hostId);
        // 尝试进行同步 检查是否启动
        String version = null;
        for (int i = 0; i < 5; i++) {
            Threads.sleep(Const.MS_S_10);
            version = monitorAgents.syncMonitorAgent(hostId, monitor.getMonitorUrl(), monitor.getAccessToken());
            this.appendLog("检查agent状态 第{}次", i + 1);
            if (version != null) {
                break;
            }
        }
        if (version == null) {
            throw Exceptions.runtime("获取 agent 状态失败");
        }
        this.appendLog("agent启动成功 version: {}", version);
        // 更新状态以及版本
        monitor.setMonitorStatus(MonitorStatus.RUNNING.getStatus());
        monitor.setAgentVersion(version);
        hostMonitorRepository.update(monitor);
        // 发送站内信
        this.sendWebSideMessage(MessageType.MACHINE_AGENT_INSTALL_SUCCESS, host, appAccountDTO);
    }

    /**
     * 发送站内信
     */
    private void sendWebSideMessage(MessageType type, Host host, AppAccountDTO appAccountDTO) {
        Map<String, Object> params = Maps.newMap();
        params.put(EventKeys.NAME, host.getName());
        WebSideMessage message = new WebSideMessage();
        message.setMessageClassify(Converts.toByte(type.getClassify().getClassify()));
        message.setMessageType(MessageType.MACHINE_ALARM.getType());
        message.setReadStatus(Converts.toByte(ReadStatus.UNREAD.getStatus()));
        message.setToUserId(appAccountDTO.getId());
        message.setToUserName(appAccountDTO.getAccountName());
        message.setSendMessage(Strings.format(MessageType.MACHINE_ALARM.getTemplate(), params));
        message.setRelId(host.getHostId().getHostId());
        message.setIsDeleted(DeleteStatusEnum.NO.getValue());
        webSideMessageService.save(message);
    }

    /**
     * 获取启动脚本
     *
     * @param agentJarPath agentJar 路径
     * @return 脚本内容
     */
    private String getStartScript(String agentJarPath, String machineId) {
        Map<Object, Object> param = Maps.newMap();
        param.put("processName", MonitorConst.AGENT_FILE_NAME_PREFIX);
        param.put("machineId", machineId);
        param.put("agentJarPath", agentJarPath);
        return Strings.format(MonitorConst.START_SCRIPT_VALUE, param);
    }

    /**
     * 拼接日志
     *
     * @param logString log
     * @param args      args
     */
    @SneakyThrows
    private void appendLog(String logString, Object... args) {
        if (!Arrays1.isEmpty(args)) {
            log.info("安装监控插件-" + logString, args);
        }
        if (logStream != null) {
            logStream.write(Strings.bytes(Strings.format(logString, args)));
            logStream.write(Letters.LF);
            logStream.flush();
        }
    }

}
